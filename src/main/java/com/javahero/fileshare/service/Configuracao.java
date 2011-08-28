package com.javahero.fileshare.service;

import java.io.File;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.document.mongodb.MongoTemplate;
import org.springframework.stereotype.Component;

@Component
public class Configuracao {
	
	// atributos
	
	private static final Logger log = Logger.getLogger(RepositorioArquivos.class);
	
	private String dataPath;
	private File dataPathDir;
	private String home;

	// regras de negócio
	
	@PostConstruct
	public void init() {
		Map<String, String> env = System.getenv();
		home = env.containsKey("FILESHARE_DATA") ? env.get("FILESHARE_HOME") : "/opt/fileshare";
		dataPath = env.containsKey("FILESHARE_DATA") ? env.get("FILESHARE_DATA") : home + File.separator + "data";	
		dataPathDir = new File(getDataPath());
		
		log.info("--------------- Configuração de Ambiente ---------------");
		log.info("--- FILESHARE_HOME: "+home);
		log.info("--- FILESHARE_DATA: "+dataPath);
		log.info("--------------------------------------------------------");
		
		if (!dataPathDir.exists()) {
			throw new RuntimeException("O caminho "+getDataPath()+" não existe");
		}
		
		if (!dataPathDir.canWrite()) {
			throw new RuntimeException("O usuário da aplicação não pode escrever no caminho "+getDataPath());
		}
		
		if (!dataPathDir.isDirectory()) {
			throw new RuntimeException("O caminho "+getDataPath()+" precisa ser um diretório");
		}
	}
	
	// métodos de acesso
	
	public String getDataPath() {
		return dataPath;
	}

	public void setDataPath(String dataPath) {
		this.dataPath = dataPath;
	}
	
	public File getDataPathDir() {
		return dataPathDir;
	}
}
