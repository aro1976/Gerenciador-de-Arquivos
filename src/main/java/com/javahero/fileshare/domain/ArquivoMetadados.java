package com.javahero.fileshare.domain;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.data.annotation.Id;
import org.springframework.data.document.mongodb.mapping.Document;

/**
 * Utilizado para representar os Metadados associados ao Arquivo
 * 
 * @author Alessandro Ramos de Oliveira <alessandro.oliveira@me.com>
 * @category Domain
 */
@Document
@XmlRootElement(name="arquivo")
public class ArquivoMetadados {

	// atributos
	
	@Id
	private String id;

	private String nomeOriginal;

	private Date dataCarregamento;

	private Date dataAcesso;
	
	private Long contadorAcesso;

	private Long tamanho;

	private String tipoConteudo;

	private String notas;
	
	// construtor
	
	public ArquivoMetadados() {
	}

	// métodos de acesso

	public String getId() {
		return id;
	}

	public void setId(String hash) {
		this.id = hash;
	}

	public String getNomeOriginal() {
		return nomeOriginal;
	}

	public void setNomeOriginal(String nome) {
		this.nomeOriginal = nome;
	}

	public Date getDataCarregamento() {
		return dataCarregamento;
	}

	public void setDataCarregamento(Date dataCarregamento) {
		this.dataCarregamento = dataCarregamento;
	}

	public Date getDataAcesso() {
		return dataAcesso;
	}

	public void setDataAcesso(Date dataAcesso) {
		this.dataAcesso = dataAcesso;
	}

	public Long getContadorAcesso() {
		return contadorAcesso;
	}

	public void setContadorAcesso(Long contadorAcesso) {
		this.contadorAcesso = contadorAcesso;
	}

	public Long getTamanho() {
		return tamanho;
	}

	public void setTamanho(Long tamanho) {
		this.tamanho = tamanho;
	}

	public String getTipoConteudo() {
		return tipoConteudo;
	}

	public void setTipoConteudo(String tipoConteudo) {
		this.tipoConteudo = tipoConteudo;
	}

	public String getNotas() {
		return notas;
	}

	public void setNotas(String notas) {
		this.notas = notas;
	}

	// sobrecarga object
	
	@Override
	public String toString() {
		return "ArquivoMetadados [id=" + id + ", nomeOriginal=" + nomeOriginal
				+ ", tipoConteudo=" + tipoConteudo + ", tamanho=" + tamanho
				+ ", dataCarregamento=" + dataCarregamento + ", dataAcesso="
				+ dataAcesso + ", contadorAcesso=" + contadorAcesso + "]";
	}
}
