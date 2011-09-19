package com.javahero.documento.service;

import static org.springframework.data.document.mongodb.query.Criteria.where;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.document.mongodb.MongoTemplate;
import org.springframework.data.document.mongodb.query.Index;
import org.springframework.data.document.mongodb.query.Order;
import org.springframework.data.document.mongodb.query.Query;
import org.springframework.data.document.mongodb.query.Update;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.stereotype.Repository;

import com.javahero.arquivo.domain.ArquivoMetadados;
import com.javahero.documento.domain.Documento;

/**
 * Gerencia a persisência dos documntos no MongoDB
 * 
 * @author Alessandro Ramos de Oliveira <alessandro.oliveira@me.com>
 * @category Service
 */
@Repository
public class RepositorioDocumento {

	// atributos
	
	private static final Logger log = Logger.getLogger(RepositorioDocumento.class);

	public static final int LIMITE = 20;
	
	@Autowired
	MongoTemplate mongoTemplate;

	// regras de negócio
	
	@PostConstruct
	public void inicializar() {
		log.info("inicializar");
		
		if (!mongoTemplate.collectionExists(Documento.class)) {
			mongoTemplate.createCollection(Documento.class);
		}
	}
	
	public void gravar(Documento documento) {
		log.info("gravar "+documento);
		
		mongoTemplate.save(documento);
		if (log.isDebugEnabled()) log.debug("salvo: "+documento);
	}
	
	public List<Documento> buscarTodos() {
		log.info("buscarTodos");
		
		List<Documento> results = mongoTemplate.findAll(Documento.class);
		
		if (log.isDebugEnabled()) log.debug("results: "+results);
		return results;
	}

	public List<Documento> buscarPorCriterio(Documento criterio) {
		log.info("buscarPorCriterio "+criterio);
		
		Query query = new Query();
		if (criterio.getTipo()!=null) {
			query.addCriteria(where("tipo").is(criterio.getTipo().toString()));
		}
		if (criterio.getNumero()!=null && !criterio.getNumero().isEmpty()) {
			query.addCriteria(where("numero").is(criterio.getNumero()));
		}
		List<Documento> results = mongoTemplate.find(query, Documento.class);
		
		if (log.isDebugEnabled()) log.debug("results: "+results);
		return results;
	}
	
	public List<Documento> buscarPorCriterio(Documento criterio, int pagina) {
		log.info("buscarPorCriterio "+criterio+ " pagina "+pagina);
		
		Query query = new Query();
		query.skip((pagina-1)*LIMITE);
		query.limit(LIMITE);

		if (criterio.getTipo()!=null) {
			query.addCriteria(where("tipo").is(criterio.getTipo().toString()));
		}
		if (criterio.getNumero()!=null && !criterio.getNumero().isEmpty()) {
			query.addCriteria(where("numero").is(criterio.getNumero()));
		}
		List<Documento> results = mongoTemplate.find(query, Documento.class);
		
		if (log.isDebugEnabled()) log.debug("results: "+results);
		return results;
	}

	public Documento buscar(String id) {
		log.info("buscar "+id);		
		return mongoTemplate.findById(id, Documento.class); 
	}
	
	public void exclui(String id) {
		log.info("exclui "+id);
		
		Query query = new Query(where("id").is(id));
		mongoTemplate.findAndRemove(query, Documento.class); 
	}
}
