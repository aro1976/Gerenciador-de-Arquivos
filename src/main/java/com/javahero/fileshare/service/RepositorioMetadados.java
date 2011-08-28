package com.javahero.fileshare.service;

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
import org.springframework.stereotype.Repository;

import com.javahero.fileshare.domain.ArquivoMetadados;

@Repository
public class RepositorioMetadados {

	private static final Logger log = Logger.getLogger(RepositorioMetadados.class);
	
	@Autowired
	MongoTemplate mongoTemplate;

	@PostConstruct
	public void inicializar() {
		log.info("inicializar");
		
		if (!mongoTemplate.collectionExists(ArquivoMetadados.class)) {
			mongoTemplate.createCollection(ArquivoMetadados.class);
		}
		
		mongoTemplate.ensureIndex(new Index()
			.named("arquivoMetadados_por_nomeOriginal")
			.on("nomeOriginal", Order.ASCENDING), ArquivoMetadados.class);
	}
	
	public void gravar(ArquivoMetadados arquivoMetadados) {
		log.info("gravar "+arquivoMetadados);
		
		mongoTemplate.save(arquivoMetadados);
		if (log.isDebugEnabled()) log.debug("salvo: "+arquivoMetadados);
	}
	
	public List<ArquivoMetadados> buscarTodos() {
		log.info("buscarTodos");
		
		List<ArquivoMetadados> results = mongoTemplate.findAll(ArquivoMetadados.class);
		
		if (log.isDebugEnabled()) log.debug("results: "+results);
		return results;
	}

	public ArquivoMetadados buscarPorHash(String hash) {
		log.info("buscarPorHash "+hash);
		
		ArquivoMetadados file = mongoTemplate.findById(hash,ArquivoMetadados.class);
		
		if (log.isDebugEnabled()) log.debug("results: "+file);
		return file;	
	}

	public List<ArquivoMetadados> buscarPorNomeOriginal(String nomeOriginal) {
		log.info("buscarPorNomeOriginal "+nomeOriginal);
		
		List<ArquivoMetadados> results = mongoTemplate.find(
				new Query(where("nomeOriginal").is(nomeOriginal)), ArquivoMetadados.class);
		
		if (log.isDebugEnabled()) log.debug("results: "+results);
		return results;
	}
	
	public void incrementarContadorAcesso(String hash) {
		log.info("incrementarContadorAcesso "+hash);
		
		Query query = new Query(where("id").is(hash));
		Update update = new Update();
        update.set("dataAcesso", new Date());
        update.inc("contadorAcesso", 1);
        
        mongoTemplate.updateMulti(query, update, ArquivoMetadados.class); 
	}

	public void exclui(String hash) {
		log.info("exclui "+hash);
		
		Query query = new Query(where("id").is(hash));
		mongoTemplate.findAndRemove(query, ArquivoMetadados.class); 
	}
}
