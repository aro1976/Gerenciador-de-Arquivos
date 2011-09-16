package com.javahero.fileshare.security;

import java.io.Serializable;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;

import com.javahero.fileshare.domain.ArquivoMetadados;

public class ArquivoPermissionEvaluator implements PermissionEvaluator {

	public boolean hasPermission(Authentication auth, Object target, Object perm) {		
		if (target instanceof EntidadeSegura) {
			EntidadeSegura es = (EntidadeSegura) target;
			if (target instanceof ArquivoMetadados) {
				ArquivoMetadados am = (ArquivoMetadados)target;
				if (perm.equals("leitura")) {
					es.setExcluivel(auth.getName().equals(am.getUsuarioCriou()));
					es.setAtualizavel(auth.getName().equals(am.getUsuarioCriou()));
					return true;
				}
			}

		}
		throw new UnsupportedOperationException("hasPermission not supported for object <"+target+"> and permission <"+perm+">");
	}

	public boolean hasPermission(Authentication authentication,
			Serializable targetId, String targetType, Object permission) {
		throw new UnsupportedOperationException("Not supported");
	}

}
