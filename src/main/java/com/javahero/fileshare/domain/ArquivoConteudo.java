package com.javahero.fileshare.domain;

import java.io.IOException;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * @author Alessandro Ramos de Oliveira <alessandro.oliveira@me.com>
 * @category Model
 */
public class ArquivoConteudo {
	
	// atributos
	
	private static final Logger log = Logger.getLogger(ArquivoConteudo.class);
	
	private String hash = null;
	
	private CommonsMultipartFile arquivo;
	
	// construtor
	
	public ArquivoConteudo() {
	}
	
	// regras de negócio
	
	public String calcularHash() {
		log.debug("calcularHash "+arquivo.getOriginalFilename());
		
		if (hash == null) {
			try {
				hash = DigestUtils.shaHex((arquivo.getInputStream()));
			} catch (IOException e) {
				throw new RuntimeException("Não foi possível gerar o Hash para o arquivo "+arquivo.getOriginalFilename(),e);
			}
			if (log.isDebugEnabled()) log.debug("O hash gerado é "+hash); 
		}
		
		return hash;
	}
	
	// metodos de acesso
	
	public CommonsMultipartFile getArquivo() {
		return arquivo;
	}
	
	public void setArquivo(CommonsMultipartFile arquivo) {
		this.arquivo = arquivo;
	}	
	
	// sobrecarga object
	
	@Override
	public String toString() {
		return "ArquivoConteudo [arquivo=" + arquivo + ", hash="+ calcularHash()+"]";
	}
}
