package com.javahero.arquivo.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * Configuração do Sistema
 * 
 * @author Alessandro Ramos de Oliveira <alessandro.oliveira@me.com>
 * @category Service
 */
@Component
public class Configuracao {
	
	// atributos
	
	private static final Logger log = Logger.getLogger(RepositorioArquivos.class);

	public static final String CONFIG_BASE_URL = "baseUrl";
	
	private String dataPath;
	private File dataPathDir;
	private String confPath;
	private File confPathDir;
	private String home;

	private Properties properties;

	// regras de negócio
	
	@PostConstruct
	public void init() {
		Map<String, String> env = System.getenv();
		home = env.containsKey("FILESHARE_HOME") ? env.get("FILESHARE_HOME") : "/opt/fileshare";
		dataPath = env.containsKey("FILESHARE_DATA") ? env.get("FILESHARE_DATA") : home + File.separator + "data";	
		confPath = env.containsKey("FILESHARE_CONF") ? env.get("FILESHARE_CONF") : home + File.separator + "etc";	
		dataPathDir = new File(getDataPath());
		confPathDir = new File(getConfPath());
		
		log.info("--------------- Configuração de Ambiente ---------------");
		log.info("--- FILESHARE_HOME: " + home);
		log.info("--- FILESHARE_DATA: " + dataPath);
		log.info("--- FILESHARE_CONF: " + confPathDir);
		log.info("--------------------------------------------------------");
		
		if (!dataPathDir.exists()) {
			throw new RuntimeException("O caminho "+dataPath+" não existe");
		}
		
		if (!dataPathDir.canWrite()) {
			throw new RuntimeException("O usuário da aplicação não pode escrever no caminho "+dataPath);
		}
		
		if (!dataPathDir.isDirectory()) {
			throw new RuntimeException("O caminho "+dataPath+" precisa ser um diretório");
		}
		
		if (!confPathDir.exists()) {
			throw new RuntimeException("O caminho "+confPath+" não existe");
		}
		
		if (!confPathDir.canWrite()) {
			throw new RuntimeException("O usuário da aplicação não pode escrever no caminho "+confPath);
		}
		
		if (!confPathDir.isDirectory()) {
			throw new RuntimeException("O caminho "+confPath+" precisa ser um diretório");
		}
		
		File configFile = new File(confPathDir,"fileshare.properties");
		properties = new Properties();
		try {
			properties.load(new FileInputStream(configFile));
		} catch (FileNotFoundException e) {
			log.error("nao foi possivel encontrar o arquivo "+configFile.getAbsolutePath());
		} catch (IOException e) {
			log.error("nao foi possivel ler o arquivo "+configFile.getAbsolutePath(), e);
		}
	}
	
	// métodos de acesso
	
	public String getDataPath() {
		return dataPath;
	}

	public void setDataPath(String dataPath) {
		this.dataPath = dataPath;
	}
	
	public String getConfPath() {
		return confPath;
	}

	public void setConfPath(String confPath) {
		this.confPath = confPath;
	}

	// regras de negócio
	
	public File getConfPathDir() {
		return confPathDir;
	}

	public File getDataPathDir() {
		return dataPathDir;
	}
	
	public String getProperty(String key) {
		return properties.getProperty(key);
	}
}
