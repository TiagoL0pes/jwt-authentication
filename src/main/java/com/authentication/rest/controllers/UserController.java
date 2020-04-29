package com.authentication.rest.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.authentication.data.dtos.UserDto;
import com.authentication.data.models.User;
import com.authentication.rest.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping
	public ResponseEntity<UserDto> create(@RequestBody @Valid User form) {
		UserDto dto = userService.create(form);
		return ResponseEntity.ok(dto);
	}

	@GetMapping("{id}")
	public ResponseEntity<UserDto> findById(@PathVariable Long id) {
		UserDto dto = userService.findById(id);
		return ResponseEntity.ok(dto);
	}

	@GetMapping
	public ResponseEntity<List<UserDto>> findAll(Pageable pageable) {
		List<UserDto> dtos = userService.findAll(pageable);
		return ResponseEntity.ok(dtos);
	}

	@PutMapping("{id}")
	public ResponseEntity<UserDto> update(@PathVariable Long id, @RequestBody User form) {
		UserDto dto = userService.update(id, form);
		return ResponseEntity.ok(dto);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		userService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
