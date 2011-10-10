package com.javahero.processo.service;

import static org.springframework.data.document.mongodb.query.Criteria.where;

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.document.mongodb.MongoTemplate;
import org.springframework.data.document.mongodb.query.Query;
import org.springframework.stereotype.Repository;

import com.javahero.documento.domain.Documento;
import com.javahero.processo.domain.Processo;


/**
 * Gerencia a persisência dos processos no MongoDB
 * 
 * @author Alessandro Ramos de Oliveira <alessandro.oliveira@me.com>
 * @category Service
 */
@Repository
public class RepositorioProcesso {

	// atributos
	
	private static final Logger log = Logger.getLogger(RepositorioProcesso.class);

	public static final int LIMITE = 20;
	
	@Autowired
	MongoTemplate mongoTemplate;

	// regras de negócio
	
	@PostConstruct
	public void inicializar() {
		log.info("inicializar");
		
		if (!mongoTemplate.collectionExists(Processo.class)) {
			mongoTemplate.createCollection(Processo.class);
		}
	}
	
	public void gravar(Processo processo) {
		log.info("gravar "+processo);
		
		mongoTemplate.save(processo);
		if (log.isDebugEnabled()) log.debug("salvo: "+processo);
	}
	
	public List<Processo> buscarTodos() {
		log.info("buscarTodos");
		
		List<Processo> results = mongoTemplate.findAll(Processo.class);
		
		if (log.isDebugEnabled()) log.debug("results: "+results);
		return results;
	}

	public Processo buscar(String id) {
		log.info("buscar "+id);		
		return mongoTemplate.findById(id, Processo.class); 
	}
	
	public Processo buscarPorNumero(String numero) {
		log.info("buscarPorNumero "+numero);
		
		Query query = new Query();
		query.addCriteria(where("numero").is(numero));
		Processo results = mongoTemplate.findOne(query, Processo.class);
		
		if (log.isDebugEnabled()) log.debug("results: "+results);
		return results;
	}
	
	public void exclui(String id) {
		log.info("exclui "+id);
		
		Query query = new Query(where("id").is(id));
		mongoTemplate.findAndRemove(query, Processo.class); 
	}
}
