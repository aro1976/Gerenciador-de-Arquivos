package com.javahero.fileshare.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.javahero.fileshare.domain.ArquivoConteudo;
import com.javahero.fileshare.domain.ArquivoMetadados;
import com.javahero.fileshare.service.Configuracao;
import com.javahero.fileshare.service.RepositorioArquivos;
import com.javahero.fileshare.service.RepositorioMetadados;

/**
 * Controla o acesso á aplicação, delegando a execução das regras de negócio para os serviços e 
 * direcionando para a visualização correta.
 * 
 * @author Alessandro Ramos de Oliveira <alessandro.oliveira@me.com>
 * @category Domain
 */
@Controller
@RequestMapping(value = "/arquivo")
public class ArquivoController {
	
	// atributos
	
	private static final Logger log = Logger.getLogger(ArquivoController.class);
	
	private static final SimpleDateFormat httpDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
	 
	static{
		httpDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
	}
	
	public synchronized static String getHttpDate(Date date){
		return httpDateFormat.format(date);
	}
	
	@Autowired
	RepositorioMetadados repositorioMetadados;
	
	@Autowired
	RepositorioArquivos repositorioArquivos;
	
	@Autowired
	Configuracao fileConfig;
	
	// tratamento de requisições
	
	@RequestMapping(value="carregar", method = RequestMethod.GET)
	public String selecionarArquivo(Model model) {
		log.info("selecionarArquivo");
		
		model.addAttribute(new ArquivoConteudo());
		return "arquivo/carregar";
	}
	
	@RequestMapping(value="carregar", method = RequestMethod.POST)
	public String carregarArquivo(ArquivoConteudo arquivoConteudo) {
		log.info("carregarArquivo "+arquivoConteudo.getArquivo().getOriginalFilename());
		
		String hash = arquivoConteudo.calcularHash();
		ArquivoMetadados metadados = repositorioMetadados.buscarPorHash(hash);
		
		if (metadados == null) {
			metadados = new ArquivoMetadados();
			metadados.setId(hash);
			metadados.setDataCarregamento(new Date());
			metadados.setNomeOriginal(arquivoConteudo.getArquivo().getOriginalFilename());
			metadados.setTipoConteudo(arquivoConteudo.getArquivo().getContentType());
			metadados.setTamanho(arquivoConteudo.getArquivo().getSize());
			repositorioMetadados.gravar(metadados);
			repositorioArquivos.gravar(arquivoConteudo);
		}
		
		return "redirect:editar/"+hash;
	}
	
	@RequestMapping(value="editar/{hash}", method = RequestMethod.GET)
	public ModelAndView editarMetadados(@PathVariable String hash, ModelAndView mav, HttpServletResponse response) throws IOException {
		log.info("editarMetadados "+hash);
		
		ArquivoMetadados metadados = repositorioMetadados.buscarPorHash(hash);
		if (metadados == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
		
		mav.addObject("arquivo", metadados);
		mav.setViewName("arquivo/editar");
		return mav;
	}
	
	@RequestMapping(value="editar/{hash}", method = RequestMethod.POST)
	public String editarMetadados(@PathVariable String hash, ArquivoMetadados atualizado) {
		log.info("editarMetadados " + hash + "atualizado: " + atualizado);
		
		ArquivoMetadados metadados = repositorioMetadados.buscarPorHash(hash);
		metadados.setNomeOriginal(atualizado.getNomeOriginal());
		metadados.setNotas(atualizado.getNotas());
		
		repositorioMetadados.gravar(metadados);
		
		return "redirect:/app/arquivo/listar";
	}
	
	@RequestMapping(value="listar", method = RequestMethod.GET)
	public ModelAndView listar(Model model) throws Exception {
		log.info("listar");
		
		ModelAndView modelAndView = new ModelAndView("arquivo/listar");
		model.addAttribute("lista", repositorioMetadados.buscarTodos());
		return modelAndView;
	}
	
	@RequestMapping(value="consultar", method = RequestMethod.GET)
	public ModelAndView consultar(ArquivoMetadados parametros) throws Exception {
		log.info("consultar");
		
		ModelAndView mav = new ModelAndView("arquivo/consultar");
		if (parametros.getNomeOriginal() != null && parametros.getNomeOriginal().length() > 0) {
			mav.setViewName("arquivo/listar");
			mav.addObject("lista", repositorioMetadados.buscarPorNomeOriginal(parametros.getNomeOriginal()));
			return mav;
		}
		
		return mav;
	}

	@RequestMapping(value="descarregar/{hash}", method=RequestMethod.GET)
	public void descarregar(@PathVariable String hash, HttpServletResponse response) throws IOException {
		log.info("descarregar "+hash);
		
		if (!repositorioArquivos.existe(hash)) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
		
		ArquivoMetadados metadata = repositorioMetadados.buscarPorHash(hash);
		File arquivo = repositorioArquivos.buscarPorHash(hash);
		response.addHeader("Last-Modified", getHttpDate(metadata.getDataCarregamento()));
		response.setContentType(metadata.getTipoConteudo());
		response.setContentLength(metadata.getTamanho().intValue());
		response.setHeader("Content-Disposition", "attachment; filename=\""
				+ metadata.getNomeOriginal() + "\"");
		
		FileInputStream fileInputStream = new FileInputStream(arquivo);
		FileCopyUtils.copy(fileInputStream, response.getOutputStream());
		
		repositorioMetadados.incrementarContadorAcesso(hash);
	}
	
	@RequestMapping(value="excluir/{hash}", method=RequestMethod.POST)
	public String excluir(@PathVariable String hash) throws IOException {
		log.info("excluir "+hash);
		
		repositorioArquivos.exclui(hash);
		repositorioMetadados.exclui(hash);
		
		return "redirect:/app/arquivo/listar";
	}
}
