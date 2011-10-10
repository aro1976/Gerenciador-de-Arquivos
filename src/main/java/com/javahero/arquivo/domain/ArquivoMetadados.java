package com.javahero.arquivo.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.data.annotation.Id;
import org.springframework.data.document.mongodb.mapping.DBRef;
import org.springframework.data.document.mongodb.mapping.Document;

import com.javahero.documento.domain.Documento;
import com.javahero.processo.domain.Processo;

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

	private String usuarioCriou;
	
	private String usuarioAtualizou;
	
	private String notas;
	
	@DBRef
	private Set<Processo> processos;
	
	@DBRef
	private Documento documento;
	
	// construtor
	
	public ArquivoMetadados() {
		this.documento = new Documento();
	}

	// regras de negócio
	
	public void associaProcesso(Processo processo) {
		this.getProcessos().add(processo);
	}
	
	public void desassociaProcesso(Processo processo) {
		this.getProcessos().remove(processo);
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

	public String getUsuarioCriou() {
		return usuarioCriou;
	}

	public void setUsuarioCriou(String usuarioCriou) {
		this.usuarioCriou = usuarioCriou;
	}

	public String getUsuarioAtualizou() {
		return usuarioAtualizou;
	}

	public void setUsuarioAtualizou(String usuarioAtualizou) {
		this.usuarioAtualizou = usuarioAtualizou;
	}

	public String getNotas() {
		return notas;
	}

	public void setNotas(String notas) {
		this.notas = notas;
	}

	public Documento getDocumento() {
		return documento;
	}

	public void setDocumento(Documento documento) {
		this.documento = documento;
	}
	
	public Set<Processo> getProcessos() {
		if (this.processos == null) {
			this.processos = new HashSet<Processo>();
		}
		return this.processos;
	}
	
	public void setProcessos(Set<Processo> processos) {
		this.processos = processos;
	}
	
	// sobrecarga object

	@Override
	public String toString() {
		return "ArquivoMetadados [id=" + id + ", nomeOriginal=" + nomeOriginal
				+ ", dataCarregamento=" + dataCarregamento + ", dataAcesso="
				+ dataAcesso + ", contadorAcesso=" + contadorAcesso
				+ ", tamanho=" + tamanho + ", tipoConteudo=" + tipoConteudo
				+ ", usuarioCriou=" + usuarioCriou + ", usuarioAtualizou="
				+ usuarioAtualizou + ", notas=" + notas + ", documento=" 
				+ documento + ", processos=" + processos + "]";
	}
}
