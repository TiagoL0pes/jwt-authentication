package com.authentication.data.models;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long id;

	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "account_non_expired", columnDefinition = "boolean DEFAULT true")
	private Boolean accountNonExpired = true;

	@Column(name = "account_non_locked", columnDefinition = "boolean DEFAULT true")
	private Boolean accountNonLocked = true;

	@Column(name = "credentials_non_expired", columnDefinition = "boolean DEFAULT true")
	private Boolean credentialsNonExpired = true;

	@Column(name = "enabled", columnDefinition = "boolean DEFAULT true")
	private Boolean enabled = true;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "user_authority", joinColumns = {
			@JoinColumn(name = "user_id", referencedColumnName = "user_id") }, inverseJoinColumns = {
					@JoinColumn(name = "authority_id", referencedColumnName = "authority_id") })
	private Set<Authority> authorities = new HashSet<>();

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "user_role", joinColumns = {
			@JoinColumn(name = "user_id", referencedColumnName = "user_id") }, inverseJoinColumns = {
					@JoinColumn(name = "role_id", referencedColumnName = "role_id") })
	private Set<Role> roles = new HashSet<>();

	public void addRoles(Collection<Role> roles) {
		Set<Role> filteredRoles = roles.stream()
			.filter(e -> !this.roles.contains(e))
			.collect(Collectors.toSet());
		
		filteredRoles.forEach(e -> this.roles.add(e));
	}

	public void addAuthorities(Collection<Authority> authorities) {
 		Set<Authority> filteredAuthorities = authorities.stream()
		.filter(e -> !this.authorities.contains(e))
		.collect(Collectors.toSet());
	
 		filteredAuthorities.forEach(e -> this.authorities.add(e));
	}
}
