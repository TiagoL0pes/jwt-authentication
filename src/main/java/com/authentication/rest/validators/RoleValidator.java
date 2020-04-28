package com.authentication.rest.validators;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.authentication.data.models.Role;
import com.authentication.rest.repositories.RoleRepository;

public class RoleValidator implements PermissionValidator<Role> {
	
	@Autowired
	private RoleRepository repository;

	@Override
	public Collection<Role> validate(Collection<Role> entities) {
		return entities.stream()
			.map(e -> repository.findById(e.getId()).orElse(new Role()))
			.filter(e -> e.getId() != null)
			.collect(Collectors.toList());
	}

}
