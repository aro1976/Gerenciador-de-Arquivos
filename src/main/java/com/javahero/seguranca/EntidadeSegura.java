package com.javahero.seguranca;

public interface EntidadeSegura {
	
	public boolean isExcluivel();

	public void setExcluivel(boolean excluivel);
 
	public boolean isAtualizavel();
	
	public void setAtualizavel(boolean atualizavel);
}
