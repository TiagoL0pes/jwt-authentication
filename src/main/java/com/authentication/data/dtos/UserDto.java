package com.authentication.data.dtos;

import java.util.Collection;

import lombok.Data;

@Data
public class UserDto {

	private Long id;
	private String email;
	private Collection<RoleDto> roles;
	private Collection<AuthorityDto> authorities;
}
