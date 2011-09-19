package com.javahero.documento.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.javahero.arquivo.service.RepositorioMetadados;
import com.javahero.documento.domain.Documento;
import com.javahero.documento.domain.DocumentoList;
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
@RequestMapping(value="documentos")
public class DocumentoController {
	
	// atributos
	
	private static final Logger log = Logger.getLogger(DocumentoController.class);
	
	
	@Autowired
	RepositorioDocumento repositorioDocumentos;
	
	@Autowired
	ServicoSeguranca servicoSeguranca;
	
	// tratamento de requisições
	
	@RequestMapping(value="/novo", method = RequestMethod.GET)
	public String novo(Model model) {
		log.info("novo");
		
		model.addAttribute(new Documento());
		return "documentos/novo";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String novo(Documento documento) {
		log.info("novo " + documento);
		
		repositorioDocumentos.gravar(documento);
		return "redirect:/documentos/listar/1";
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ModelAndView editar(@PathVariable String id, HttpServletResponse response) throws IOException {
		log.info("editar "+id);
		
		Documento documento = repositorioDocumentos.buscar(id);
		if (documento == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("documento", documento);
		mav.setViewName("documentos/editar");
		return mav;
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.PUT)
	public String atualizar(@PathVariable String id, Documento atualizado) {
		log.info("editarMetadados " + id + "atualizado: " + atualizado);
		
		Documento documento = repositorioDocumentos.buscar(id);
		documento.setNumero(atualizado.getNumero());
		documento.setTipo(atualizado.getTipo());
		repositorioDocumentos.gravar(documento);
		
		return "redirect:/documentos/listar/1";
	}
	
	//@PreAuthorize("hasRole('ROLE_USER')")
	//@PostFilter("hasPermission(filterObject, 'read') or hasPermission(filterObject, 'admin')")
	@RequestMapping(value="listar", method = RequestMethod.GET)
	public ModelAndView listar(Documento criterio, HttpServletRequest request) throws Exception {
		log.info("listar");
		
		ModelAndView mav = new ModelAndView("documentos/listar");
		List<Documento> lista = repositorioDocumentos.buscarPorCriterio(criterio);
		mav.addObject("documentos", new DocumentoList(lista));
		mav.addObject("existePaginaAnterior", false);
		mav.addObject("existePaginaPosterior", false);
		mav.addObject("paginaAtual", 1);
		mav.addObject("query",request.getQueryString());
		return mav;
	}
	
	@RequestMapping(value="listar/{pagina}", method = RequestMethod.GET)
	public ModelAndView listarPagina(@PathVariable(value="pagina") int pagina, Documento criterio, HttpServletRequest request) throws Exception {
		log.info("listarPAgina "+pagina);
		
		ModelAndView mav = new ModelAndView("documentos/listarPagina");
		List<Documento> lista = repositorioDocumentos.buscarPorCriterio(criterio, pagina);
		mav.addObject("documentos", new DocumentoList(lista));
		mav.addObject("existePaginaAnterior", pagina > 1);
		mav.addObject("existePaginaPosterior", lista.size() == RepositorioMetadados.LIMITE);
		mav.addObject("paginaAtual", pagina);
		mav.addObject("query",request.getQueryString());
		return mav;
	}
	
	@RequestMapping(value="/consultar", method = RequestMethod.GET)
	public ModelAndView consultar(Documento criterio) throws Exception {
		log.info("consultar");
		
		return new ModelAndView("documentos/consultar");
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public String excluir(@PathVariable String id) throws IOException {
		log.info("excluir "+id);
		
		repositorioDocumentos.exclui(id);
		
		return "redirect:/documentos/listar/1";
	}
}
