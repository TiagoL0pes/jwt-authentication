package com.authentication.rest.validators;

import java.util.Collection;

public interface PermissionValidator<T> {

	public Collection<T> validate(Collection<T> entities);
}
