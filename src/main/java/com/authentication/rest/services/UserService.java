package com.authentication.rest.services;

import static com.google.common.base.Strings.isNullOrEmpty;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.authentication.data.dtos.UserDto;
import com.authentication.data.models.Authority;
import com.authentication.data.models.Role;
import com.authentication.data.models.User;
import com.authentication.rest.repositories.AuthorityRepository;
import com.authentication.rest.repositories.RoleRepository;
import com.authentication.rest.repositories.UserRepository;
import com.authentication.rest.validators.AuthorityValidator;
import com.authentication.rest.validators.RoleValidator;
import com.authentication.util.converters.ModelConverter;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private AuthorityRepository authorityRepository;

	public UserDto create(User user) {
		// TODO
		// userRepository.save(user);
		// return ModelConverter.convertObject(user, UserDto.class);
		return new UserDto();
	}

	public UserDto findById(Long id) {
		return userRepository.findById(id).map(e -> ModelConverter.convertObject(e, UserDto.class))
				.orElseThrow(() -> new RuntimeException());
	}

	public List<UserDto> findAll(Pageable pageable) {
		return userRepository.findAll(pageable).getContent().stream()
				.map(e -> ModelConverter.convertObject(e, UserDto.class)).collect(Collectors.toList());
	}

	public UserDto update(Long id, User form) {
		User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException());

		updateEntity(user, form);
		userRepository.save(user);
		return ModelConverter.convertObject(user, UserDto.class);
	}

	public void delete(Long id) {
		User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException());
		userRepository.delete(user);
	}

	private void updateEntity(User user, User form) {
		Collection<Role> roles = new RoleValidator().validate(form.getRoles());
		Collection<Authority> authorities = new AuthorityValidator().validate(form.getAuthorities());

		user.setPassword(isNullOrEmpty(form.getPassword()) ? form.getPassword() : form.getPassword());
		user.addRoles(roles);
		user.addAuthorities(authorities);
	}

}
