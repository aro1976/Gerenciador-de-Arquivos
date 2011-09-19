package com.javahero.arquivo.service;

import java.io.File;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javahero.arquivo.domain.ArquivoConteudo;

/**
 * Gerencia a persistência dos arquivos no sistema operacional
 * 
 * @author Alessandro Ramos de Oliveira <alessandro.oliveira@me.com>
 * @category Service
 */
@Repository
public class RepositorioArquivos {

	// atributos
	
	private static final Logger log = Logger.getLogger(RepositorioArquivos.class);
	
	@Autowired
	Configuracao configuracao;
	
	// regras de negocio
	
	public void gravar(ArquivoConteudo arquivoConteudo) {
		String hash = arquivoConteudo.calcularHash();
		log.info("gravar "+arquivoConteudo);
		
		File hashDir = buscarPastaPorHash(hash);
		if (!hashDir.exists()) {
			hashDir.mkdirs();
		}
		
		File file = buscarPorHash(hash);
		try {
			arquivoConteudo.getArquivo().transferTo(file);
		} catch (Exception e) {
			throw new RuntimeException("Não foi possível persistir o arquivo "+file,e);
		}
	}
	
	protected File buscarPastaPorHash(String hash) {
		File fileDir = new File(configuracao.getDataPathDir(),hash.substring(0, 3) + "/"+ hash.substring(3, 6));
		return fileDir;
	}
	
	public File buscarPorHash(String hash) {
		return new File(buscarPastaPorHash(hash),hash.substring(6));
	}

	public boolean existe(String hash) {
		File file = buscarPorHash(hash);
		return file.exists();
	}

	public void exclui(String hash) {
		File file = buscarPorHash(hash);
		if (file.isFile() && file.canWrite()) {
			file.delete();
		}
	}
}
