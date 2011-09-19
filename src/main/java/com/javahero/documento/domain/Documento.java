package com.javahero.documento.domain;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.data.document.mongodb.mapping.Document;

/**
 * Utilizado para representar um documento
 * 
 * @author Alessandro Ramos de Oliveira <alessandro.oliveira@me.com>
 * @category Domain
 */
@Document
@XmlRootElement(name="documento")
public class Documento {
	
	public enum Tipo {
		BILL_OF_LADING, 
		HOUSE_BILL_OF_LADING,
		MASTER_BILL_OF_LADING,
		AIRWAY_BILL,
		HOUSE_AIRWAY_BILL,
		MASTER_AIRWAY_BILL,
		PACKING_LIST,
		COMERCIAL_INVOICE,
		FREIGHT_INVOICE,
	}
	
	// atributos
	
	private String id;
	
	private Tipo tipo;
	
	private String numero;	
	
	// construtor
	
	public Documento() {
	}
	
	// metodos de acesso
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	// sobrecarga object
	
	@Override
	public String toString() {
		return "Documento [id=" + id + ", tipo=" + tipo + ", numero=" + numero
				+ "]";
	}

}