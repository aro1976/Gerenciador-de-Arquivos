package br.com.dynamicflow.fileshare.service;

import static org.springframework.data.document.mongodb.query.Criteria.where;

import java.util.Arrays;
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

@Repository
public class FileMetadataRepository {

	private static final Logger log = Logger.getLogger(FileMetadataRepository.class);
	
	@Autowired
	MongoTemplate mongoTemplate;

	@PostConstruct
	public void init() {
		log.info("init");
		
		if (!mongoTemplate.collectionExists(FileMetadata.class)) {
			mongoTemplate.createCollection(FileMetadata.class);
		}
		
		mongoTemplate.ensureIndex(new Index()
			.named("fileMetadata_by_process")
			.on("processList", Order.ASCENDING), FileMetadata.class);
		
		mongoTemplate.ensureIndex(new Index()
			.named("fileMetadata_by_documentType_and_document")
			.on("documentType", Order.ASCENDING)
			.on("document", Order.ASCENDING), FileMetadata.class);
	}
	
	public void save(FileMetadata file) {
		log.info("init");
		
		mongoTemplate.save(file);
		System.out.println("Saved: " + file);
	}

	public void incrementAccess(String id) {
		log.info("incrementAccess "+id);
		
		Query query = new Query(where("id").is(id));
		Update update = new Update();
        update.set("accessDate", new Date());
        update.inc("accessCount", 1);
        
        mongoTemplate.updateMulti(query, update, FileMetadata.class); 
	}
	
	public List<FileMetadata> findAll() {
		log.info("findAll");
		
		List<FileMetadata> results = mongoTemplate.findAll(FileMetadata.class);
		
		if (log.isDebugEnabled()) log.debug("results: "+results);
		return results;
	}

	public FileMetadata findById(String id) {
		log.info("findById "+id);
		
		FileMetadata file = mongoTemplate.findById(id,FileMetadata.class);
		
		if (log.isDebugEnabled()) log.debug("results: "+file);
		return file;	
	}

	public List<FileMetadata> findByProcess(String process) {
		log.info("findByProcess "+process);
		
		List<FileMetadata> results = mongoTemplate.find(
				new Query(where("processList").in(process)), FileMetadata.class);
		
		if (log.isDebugEnabled()) log.debug("results: "+results);
		return results;
	}

	public List<FileMetadata> findByDocument(String documentType, String document) {
		log.info("findByDocument "+documentType+" "+document);
		
		List<FileMetadata> results = mongoTemplate.find(
				new Query(where("documentType").is(documentType).and("document").is(document)), FileMetadata.class);
		
		if (log.isDebugEnabled()) log.debug("results: "+results);
		return results;
	}
}
