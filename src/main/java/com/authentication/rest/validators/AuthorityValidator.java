package com.authentication.rest.validators;

import java.util.Set;
import java.util.stream.Collectors;

import com.authentication.data.models.Authority;
import com.authentication.rest.repositories.AuthorityRepository;

public class AuthorityValidator implements PermissionValidator<Authority> {

	private AuthorityRepository repository;
	
	public AuthorityValidator(AuthorityRepository repository) {
		this.repository = repository;
	}

	@Override
	public Set<Authority> validate(Set<Authority> entities) {
		return entities.stream()
			.map(e -> repository.findById(e.getId()).orElse(new Authority()))
			.filter(e -> e.getId() != null)
			.collect(Collectors.toSet());
	}

}
