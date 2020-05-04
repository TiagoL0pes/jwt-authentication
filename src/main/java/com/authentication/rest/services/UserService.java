package com.authentication.rest.services;

import static com.google.common.base.Strings.isNullOrEmpty;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.authentication.data.dtos.UserDto;
import com.authentication.data.models.Authority;
import com.authentication.data.models.User;
import com.authentication.rest.repositories.AuthorityRepository;
import com.authentication.rest.repositories.RoleRepository;
import com.authentication.rest.repositories.UserRepository;
import com.authentication.rest.validators.AuthorityValidator;
import com.authentication.rest.validators.RoleValidator;
import com.authentication.util.converters.ModelConverter;
import com.authentication.util.exceptions.BadRequestException;
import com.authentication.util.exceptions.ResourceNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private AuthorityRepository authorityRepository;

	public UserDto create(User user) {
		validateUsername(user);
		
		user.setRoles(new RoleValidator(roleRepository)
				.validate(user.getRoles()));
		
		user.setPermissions(new AuthorityValidator(authorityRepository).
				validate(user.getPermissions()));
		
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		userRepository.save(user);
		return ModelConverter.convertObject(user, UserDto.class);
	}

	public UserDto findById(Long id) {
		return userRepository.findById(id).map(e -> ModelConverter.convertObject(e, UserDto.class))
				.orElseThrow(() -> new ResourceNotFoundException("User not found"));
	}

	public List<UserDto> findAll(Pageable pageable) {
		return userRepository.findAll(pageable).getContent().stream()
				.map(e -> ModelConverter.convertObject(e, UserDto.class)).collect(Collectors.toList());
	}

	public UserDto update(Long id, User form) {
		User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));

		updateEntity(user, form);
		userRepository.save(user);
		return ModelConverter.convertObject(user, UserDto.class);
	}

	@Transactional
	public void delete(Long id) {
		User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
		userRepository.delete(user);
	}

	private void updateEntity(User user, User form) {
		user.addRoles(new RoleValidator(roleRepository)
				.validate(form.getRoles()));
		
		user.addPermissions(new AuthorityValidator(authorityRepository)
				.validate(form.getPermissions()));

		user.setPassword(isNullOrEmpty(form.getPassword()) ? form.getPassword() : form.getPassword());
	}

	private void validateUsername(User user) {
		Optional<User> optional = userRepository.findByEmail(user.getEmail());
		if(optional.isPresent()) {
			throw new BadRequestException("Email " + user.getEmail() + " is already registered");
		}

	}
}
