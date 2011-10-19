package com.javahero.arquivo.service;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.data.mongodb.core.query.Order;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.javahero.arquivo.domain.ArquivoMetadados;
import com.javahero.arquivo.web.ArquivoPesquisaForm;
import com.javahero.processo.service.RepositorioProcesso;

/**
 * Gerencia a persisência dos Metadados no MongoDB
 * 
 * @author Alessandro Ramos de Oliveira <alessandro.oliveira@me.com>
 * @category Service
 */
@Repository
public class RepositorioMetadados {

	// atributos
	
	private static final Logger log = Logger.getLogger(RepositorioMetadados.class);

	public static final int LIMITE = 20;
	
	@Autowired
	MongoTemplate mongoTemplate;

	@Autowired
	RepositorioProcesso repositorioProcesso;
	
	// regras de negócio
	
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

	public List<ArquivoMetadados> buscarPorCriterio(ArquivoPesquisaForm criterio) {
		log.info("buscarPorCriterio "+criterio);
		
		Query query = new Query();
		if (criterio.getNomeOriginal()!=null && !criterio.getNomeOriginal().isEmpty()) {
			query.addCriteria(where("nomeOriginal").regex("(.*)"+criterio.getNomeOriginal()+"(.*)"));
		}
		if (criterio.getNumeroProcesso()!=null && !criterio.getNumeroProcesso().isEmpty()) {
			query.addCriteria(where("processos").in(criterio.getNumeroProcesso()));
		}
		List<ArquivoMetadados> results = mongoTemplate.find(query, ArquivoMetadados.class);
		
		if (log.isDebugEnabled()) log.debug("results: "+results);
		return results;
	}
	
	public List<ArquivoMetadados> buscarPorCriterio(ArquivoPesquisaForm criterio, int pagina) {
		log.info("buscarPorCriterio "+criterio+ " pagina "+pagina);
		
		Query query = new Query();
		query.skip((pagina-1)*LIMITE);
		query.limit(LIMITE);
		if (criterio.getNomeOriginal()!=null && !criterio.getNomeOriginal().isEmpty()) {
			query.addCriteria(where("nomeOriginal").regex("(.*)"+criterio.getNomeOriginal()+"(.*)"));
		}
		if (criterio.getNumeroProcesso()!=null && !criterio.getNumeroProcesso().isEmpty()) {
			query.addCriteria(where("processos").in(criterio.getNumeroProcesso()));
		}
		List<ArquivoMetadados> results = mongoTemplate.find(query, ArquivoMetadados.class);
		
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
