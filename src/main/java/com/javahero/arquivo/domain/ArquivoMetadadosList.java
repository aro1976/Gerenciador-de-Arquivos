package com.javahero.arquivo.domain;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Utilizado para representar uma Lista de Metadados, é necessário para utilizar o Jaxb2Marshaller
 * 
 * @author Alessandro Ramos de Oliveira <alessandro.oliveira@me.com>
 * @category Domain
 * @See org.springframework.oxm.jaxb.Jaxb2Marshaller
 */
@Document
@XmlRootElement(name="arquivos")
public class ArquivoMetadadosList {

	private List<ArquivoMetadados> arquivoMetadadosList;

	public ArquivoMetadadosList() {}
	
	public ArquivoMetadadosList(List<ArquivoMetadados> arquivoMetadadosList) {
		this.arquivoMetadadosList = arquivoMetadadosList;
	}
	
	@XmlElement(name="arquivo")
	//@JsonUnwrapped
	public List<ArquivoMetadados> getArquivoMetadadosList() {
		return arquivoMetadadosList;
	}
	public void setArquivoMetadadosList(List<ArquivoMetadados> arquivoMetadadosList) {
		this.arquivoMetadadosList = arquivoMetadadosList;
	}
}
