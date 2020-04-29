package com.authentication.rest.validators;

import java.util.Set;
import java.util.stream.Collectors;

import com.authentication.data.models.Role;
import com.authentication.rest.repositories.RoleRepository;

public class RoleValidator implements PermissionValidator<Role> {
	
	private RoleRepository repository;
	
	public RoleValidator(RoleRepository repository) {
		this.repository = repository;
	}

	@Override
	public Set<Role> validate(Set<Role> entities) {
		return entities.stream()
			.map(e -> repository.findById(e.getId()).orElse(new Role()))
			.filter(e -> e.getId() != null)
			.collect(Collectors.toSet());
	}

}
