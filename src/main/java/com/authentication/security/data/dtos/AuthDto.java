package com.authentication.security.data.dtos;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import lombok.Data;

@Data
public class AuthDto {

	private String email;
	private String password;

	public UsernamePasswordAuthenticationToken generateCredencials(Collection<? extends GrantedAuthority> authorities) {
		return new UsernamePasswordAuthenticationToken(email, password, authorities);
	}
}
