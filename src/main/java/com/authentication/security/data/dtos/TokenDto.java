package com.authentication.security.data.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TokenDto {

	private String token;
	private String tipo;
}
