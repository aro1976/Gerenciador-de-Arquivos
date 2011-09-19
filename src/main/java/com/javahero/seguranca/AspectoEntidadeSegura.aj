package com.javahero.seguranca;

import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

public aspect AspectoEntidadeSegura {

	declare parents : com.javahero.**.domain.* implements EntidadeSegura;
 
 	@Transient
	private transient boolean EntidadeSegura.exlcuivel = true;
	
	@Transient
	private transient boolean EntidadeSegura.atualizavel = true;

	@JsonIgnore
	@XmlTransient
	public boolean EntidadeSegura.isExcluivel() {
		return exlcuivel;
	}

	public void EntidadeSegura.setExcluivel(boolean exlcuivel) {
		this.exlcuivel = exlcuivel;
	}

	@JsonIgnore
    @XmlTransient
	public boolean EntidadeSegura.isAtualizavel() {
		return atualizavel;
	}

	public void EntidadeSegura.setAtualizavel(boolean atualizavel) {
		this.atualizavel = atualizavel;
	}
}
