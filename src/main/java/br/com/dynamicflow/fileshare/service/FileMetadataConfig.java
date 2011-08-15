package br.com.dynamicflow.fileshare.service;

import java.io.File;
import java.net.URI;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

@Component(value="file")
public class FileMetadataConfig {
	
	// fields
	
	private String dataPath;
	private File dataPathDir;
	private String home;

	// business methods
	
	@PostConstruct
	public void init() {
		Map<String, String> env = System.getenv();
		home = env.get("FILESHARE_HOME");
		System.out.println("FILESHARE_HOME: "+home);
		
		dataPath = env.containsKey("FILESHARE_DATA") ? env.get("FILESHARE_DATA") : home + File.separator + "data";
		System.out.println("FILESHARE_DATA: "+dataPath);
		
		dataPathDir = new File(getDataPath());
		
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
	
	// access methods
	
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
