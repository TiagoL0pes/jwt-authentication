package com.authentication.rest.validators;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.authentication.data.models.Authority;
import com.authentication.rest.repositories.AuthorityRepository;

public class AuthorityValidator implements PermissionValidator<Authority> {

	@Autowired
	private AuthorityRepository repository;

	@Override
	public Collection<Authority> validate(Collection<Authority> entities) {
		return entities.stream()
			.map(e -> repository.findById(e.getId()).orElse(new Authority()))
			.filter(e -> e.getId() != null)
			.collect(Collectors.toList());
	}

}
