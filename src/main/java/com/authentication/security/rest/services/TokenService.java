package com.authentication.security.rest.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.authentication.data.models.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

	@Value("${auth.jwt.expiration}")
	private static final Long EXPIRATION_TIME = 86400000L;

	@Value("${auth.jwt.secret}")
	private static final String SECRET = "secret";

	private static final Date TODAY = new Date();

	public String generateToken(Authentication authentication) {
		User loggedUser = (User) authentication.getPrincipal();
		return Jwts.builder().setIssuer("Spring Security Application").setSubject(loggedUser.getId().toString())
				.setIssuedAt(TODAY).setExpiration(new Date(TODAY.getTime() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS256, SECRET).compact();
	}

	public boolean isTokenValid(String token) {
		try {
			Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Long getUserId(String token) {
		Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
		return Long.parseLong(claims.getSubject());
	}

}
