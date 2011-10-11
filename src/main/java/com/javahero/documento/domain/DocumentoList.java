package com.javahero.documento.domain;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Utilizado para representar uma Lista de Documentos, é necessário para utilizar o Jaxb2Marshaller
 * 
 * @author Alessandro Ramos de Oliveira <alessandro.oliveira@me.com>
 * @category Domain
 * @See org.springframework.oxm.jaxb.Jaxb2Marshaller
 */
@Document
@XmlRootElement(name="documentos")
public class DocumentoList {

	private List<Documento> documentoList;

	public DocumentoList() {}
	
	public DocumentoList(List<Documento> documentoList) {
		this.documentoList = documentoList;
	}
	
	@XmlElement(name="documento")
	//@JsonUnwrapped
	public List<Documento> getDocumentoList() {
		return documentoList;
	}
	public void setDocumentoList(List<Documento> documentoList) {
		this.documentoList = documentoList;
	}
}
