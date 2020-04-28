package com.authentication.data.dtos;

import java.util.Collection;

import com.authentication.data.models.Authority;
import com.authentication.data.models.Role;

import lombok.Data;

@Data
public class UserDto {

	private Long id;
	private String email;
	private Collection<Role> roles;
	private Collection<Authority> authorities;
}
