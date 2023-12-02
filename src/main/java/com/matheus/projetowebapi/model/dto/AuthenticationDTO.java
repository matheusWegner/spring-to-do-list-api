package com.matheus.projetowebapi.model.dto;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public record AuthenticationDTO(String email, String password) {

    public UsernamePasswordAuthenticationToken converter() {
		return new UsernamePasswordAuthenticationToken(email, password);
	}
}
