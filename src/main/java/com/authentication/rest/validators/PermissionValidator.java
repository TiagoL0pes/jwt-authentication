package com.authentication.rest.validators;

import java.util.Set;

public interface PermissionValidator<T> {

	public Set<T> validate(Set<T> entities);
}
