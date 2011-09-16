package com.javahero.fileshare.security;

import javax.persistence.Transient;

public aspect AspectoEntidadeSegura {

	declare parents : com.javahero.fileshare.domain.* implements EntidadeSegura;
 
 	@Transient
	private transient boolean EntidadeSegura.exlcuivel = false;
	
	@Transient
	private transient boolean EntidadeSegura.atualizavel = false;

	public boolean EntidadeSegura.isExcluivel() {
		return exlcuivel;
	}

	public void EntidadeSegura.setExcluivel(boolean exlcuivel) {
		this.exlcuivel = exlcuivel;
	}

	public boolean EntidadeSegura.isAtualizavel() {
		return atualizavel;
	}

	public void EntidadeSegura.setAtualizavel(boolean atualizavel) {
		this.atualizavel = atualizavel;
	}
}
