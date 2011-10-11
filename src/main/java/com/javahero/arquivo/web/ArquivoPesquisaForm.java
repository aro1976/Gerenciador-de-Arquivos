package com.javahero.arquivo.web;

public class ArquivoPesquisaForm {

	// atributos
	
	private String nomeOriginal;
	private String numeroProcesso;
	
	// construtores
	
	public ArquivoPesquisaForm() {
	}

	// access methods
	
	public String getNomeOriginal() {
		return nomeOriginal;
	}

	public void setNomeOriginal(String nomeOriginal) {
		this.nomeOriginal = nomeOriginal;
	}

	public String getNumeroProcesso() {
		return numeroProcesso;
	}

	public void setNumeroProcesso(String numeroProcesso) {
		this.numeroProcesso = numeroProcesso;
	}

	// object overrides
	
	@Override
	public String toString() {
		return "ArquivoPesquisaForm [nomeOriginal=" + nomeOriginal
				+ ", numeroProcesso=" + numeroProcesso + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((nomeOriginal == null) ? 0 : nomeOriginal.hashCode());
		result = prime * result
				+ ((numeroProcesso == null) ? 0 : numeroProcesso.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ArquivoPesquisaForm other = (ArquivoPesquisaForm) obj;
		if (nomeOriginal == null) {
			if (other.nomeOriginal != null)
				return false;
		} else if (!nomeOriginal.equals(other.nomeOriginal))
			return false;
		if (numeroProcesso == null) {
			if (other.numeroProcesso != null)
				return false;
		} else if (!numeroProcesso.equals(other.numeroProcesso))
			return false;
		return true;
	}
}
