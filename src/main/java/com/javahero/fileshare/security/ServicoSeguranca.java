package com.javahero.fileshare.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class ServicoSeguranca {
	
	public String obtemUsuario() {

		return SecurityContextHolder.getContext().getAuthentication().getName();
	}
	
	public boolean autenticado() {
		return SecurityContextHolder.getContext().getAuthentication().isAuthenticated();
	}
}
