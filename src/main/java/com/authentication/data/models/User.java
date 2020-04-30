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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "users")
public class User implements UserDetails {

	private static final long serialVersionUID = 2902742143844389415L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id", unique = true)
	private Long id;

	@Column(name = "email", nullable = false)
	@NotNull(message = "{field.not.null}")
	@NotEmpty(message = "{field.not.empty}")
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
	private Set<Authority> permissions = new HashSet<>();

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "user_role", joinColumns = {
			@JoinColumn(name = "user_id", referencedColumnName = "user_id") }, inverseJoinColumns = {
					@JoinColumn(name = "role_id", referencedColumnName = "role_id") })
	private Set<Role> roles = new HashSet<>();

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public boolean isAccountNonExpired() {
		return this.accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return this.credentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return this.enabled;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.permissions;
	}

	public void addRoles(Collection<Role> roles) {
		Set<Role> filteredRoles = roles.stream().filter(e -> !this.roles.contains(e)).collect(Collectors.toSet());

		filteredRoles.forEach(e -> this.roles.add(e));
	}

	public void addPermissions(Collection<Authority> authorities) {
		Set<Authority> filteredAuthorities = authorities.stream().filter(e -> !this.permissions.contains(e))
				.collect(Collectors.toSet());

		filteredAuthorities.forEach(e -> this.permissions.add(e));
	}

}
