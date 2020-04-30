package com.authentication.security.data.dtos;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import lombok.Data;

@Data
public class AuthDto {

	private String email;
	private String password;

	public UsernamePasswordAuthenticationToken generateCredencials() {
		return new UsernamePasswordAuthenticationToken(email, password);
	}
}
