package com.javahero.arquivo.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.javahero.arquivo.domain.ArquivoConteudo;
import com.javahero.arquivo.domain.ArquivoMetadados;
import com.javahero.arquivo.domain.ArquivoMetadadosList;
import com.javahero.arquivo.service.Configuracao;
import com.javahero.arquivo.service.RepositorioArquivos;
import com.javahero.arquivo.service.RepositorioMetadados;
import com.javahero.documento.domain.Documento;
import com.javahero.documento.domain.Documento.Tipo;
import com.javahero.documento.service.RepositorioDocumento;
import com.javahero.seguranca.ServicoSeguranca;

/**
 * Controla o acesso á aplicação, delegando a execução das regras de negócio para os serviços e 
 * direcionando para a visualização correta.
 * 
 * @author Alessandro Ramos de Oliveira <alessandro.oliveira@me.com>
 * @category WEB
 */
@Controller
@RequestMapping(value="arquivos")
public class ArquivoController {
	
	// atributos
	
	private static final Logger log = Logger.getLogger(ArquivoController.class);
	
	private static final SimpleDateFormat httpDateFormat;
	 
	static{
		httpDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
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
	RepositorioDocumento repositorioDocumento;
	
	@Autowired
	ServicoSeguranca servicoSeguranca;
	
	@Autowired
	Configuracao fileConfig;
	
	// tratamento de requisições
	
	@RequestMapping(value="/novo", method = RequestMethod.GET)
	public String selecionarArquivo(Model model) {
		log.info("selecionarArquivo");
		
		model.addAttribute(new ArquivoConteudo());
		return "arquivos/carregar";
	}
	
	@RequestMapping(method = RequestMethod.POST)
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
			metadados.setUsuarioCriou(servicoSeguranca.obtemUsuario());
			metadados.setUsuarioAtualizou(servicoSeguranca.obtemUsuario());
			repositorioMetadados.gravar(metadados);
			repositorioArquivos.gravar(arquivoConteudo);
		}
		
		return "redirect:"+hash;
	}
	
	@RequestMapping(value="/{hash}", method = RequestMethod.GET)
	public ModelAndView editarMetadados(@PathVariable String hash, HttpServletResponse response) throws IOException {
		log.info("editarMetadados "+hash);
		
		ArquivoMetadados metadados = repositorioMetadados.buscarPorHash(hash);
		if (metadados == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("arquivo", metadados);
		mav.setViewName("arquivos/editar");
		return mav;
	}
	
	@RequestMapping(value="/{hash}", method = RequestMethod.PUT)
	public String editarMetadados(@PathVariable String hash, ArquivoMetadados atualizado) {
		log.info("editarMetadados " + hash + "atualizado: " + atualizado);
		
		ArquivoMetadados metadados = repositorioMetadados.buscarPorHash(hash);
		metadados.setNomeOriginal(atualizado.getNomeOriginal());
		metadados.setNotas(atualizado.getNotas());
		metadados.setUsuarioAtualizou(servicoSeguranca.obtemUsuario());
		
		// grava o documento
		List<Documento> documentosSalvos = repositorioDocumento.buscarPorCriterio(atualizado.getDocumento());
		if (documentosSalvos.size() == 0) {
			repositorioDocumento.gravar(atualizado.getDocumento());
			metadados.setDocumento(atualizado.getDocumento());
		} else {
			metadados.setDocumento(documentosSalvos.get(0));
		}
		
		repositorioMetadados.gravar(metadados);
		
		return "redirect:/arquivos/listar/1";
	}
	
	//@PreAuthorize("hasRole('ROLE_USER')")
	//@PostFilter("hasPermission(filterObject, 'read') or hasPermission(filterObject, 'admin')")
	@RequestMapping(value="listar", method = RequestMethod.GET)
	public ModelAndView listar(ArquivoMetadados criterio, HttpServletRequest request) throws Exception {
		log.info("listar");

		ModelAndView mav = new ModelAndView("arquivos/listar");
		List<ArquivoMetadados> lista = repositorioMetadados.buscarPorCriterio(criterio);
		mav.addObject("arquivos", new ArquivoMetadadosList(lista));
		mav.addObject("existePaginaAnterior", false);
		mav.addObject("existePaginaPosterior", false);
		mav.addObject("paginaAtual", 1);
		mav.addObject("query",request.getQueryString());
		return mav;
	}
	
	@RequestMapping(value="listar/{pagina}", method = RequestMethod.GET)
	public ModelAndView listarPagina(@PathVariable(value="pagina") int pagina, ArquivoMetadados criterio, HttpServletRequest request) throws Exception {
		log.info("listarPAgina "+pagina);

		ModelAndView mav = new ModelAndView("arquivos/listarPagina");
		List<ArquivoMetadados> lista = repositorioMetadados.buscarPorCriterio(criterio, pagina);
		mav.addObject("arquivos", new ArquivoMetadadosList(lista));
		mav.addObject("existePaginaAnterior", pagina > 1);
		mav.addObject("existePaginaPosterior", lista.size() == RepositorioMetadados.LIMITE);
		mav.addObject("paginaAtual", pagina);
		mav.addObject("query",request.getQueryString());
		return mav;
	}
	
	@RequestMapping(value="/consultar", method = RequestMethod.GET)
	public ModelAndView consultar(ArquivoMetadados parametros) throws Exception {
		log.info("consultar");
		
		return new ModelAndView("arquivos/consultar");
	}

	@RequestMapping(value="/{hash}/descarregar", method=RequestMethod.GET)
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
	
	@RequestMapping(value="/{hash}", method=RequestMethod.DELETE)
	@ResponseBody
	public void excluir(@PathVariable String hash) throws IOException {
		log.info("excluir "+hash);
		
		repositorioArquivos.exclui(hash);
		repositorioMetadados.exclui(hash);
	}
	
	public List<Tipo> tipoDocumentos() {
		return Arrays.asList(Tipo.values());
	}
}
