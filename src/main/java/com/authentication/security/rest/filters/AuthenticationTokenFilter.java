package com.authentication.security.rest.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.authentication.data.models.User;
import com.authentication.rest.repositories.UserRepository;
import com.authentication.security.rest.services.TokenService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AuthenticationTokenFilter extends OncePerRequestFilter {

	private TokenService tokenService;
	private UserRepository userRespotiory;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {

		String token = recoverToken(request);
		boolean isValid = tokenService.isTokenValid(token);
		if (isValid) {
			authenticateUser(token);
		}
		chain.doFilter(request, response);
	}

	private String recoverToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if (StringUtils.isEmpty(token) || !token.startsWith("Bearer ")) {
			return null;
		}

		return token.substring(7, token.length());
	}

	private void authenticateUser(String token) {
		Long userId = tokenService.getUserId(token);
		User user = userRespotiory.findById(userId).get();
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null,
				user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

}
