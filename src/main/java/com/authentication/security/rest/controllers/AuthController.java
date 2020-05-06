package com.authentication.security.rest.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.authentication.rest.services.UserService;
import com.authentication.security.data.dtos.AuthDto;
import com.authentication.security.data.dtos.TokenDto;
import com.authentication.security.rest.services.TokenService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private TokenService tokenService;

	@Autowired
	private UserService userService;

	@PostMapping
	public ResponseEntity<?> auth(@RequestBody AuthDto form) {
		Collection<? extends GrantedAuthority> authorities = userService.findAuthoritiesByUserEmail(form.getEmail());
		UsernamePasswordAuthenticationToken login = form.generateCredencials(authorities);
		try {
			Authentication authentication = authManager.authenticate(login);
			String token = tokenService.generateToken(authentication);

			return ResponseEntity.ok(new TokenDto(token, "Bearer"));
		} catch (AuthenticationException e) {
			return ResponseEntity.badRequest().build();
		}
	}
}
