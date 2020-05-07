package com.authentication.rest.interfaces;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.authentication.data.dtos.UserDto;
import com.authentication.data.models.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

@Api(value = "User Endpoint", tags = { "User Endpoint" })
public interface IUserController {

	@ApiOperation(value = "Add a new user")
	public ResponseEntity<UserDto> create(@ApiParam("User data") User form);

	@ApiOperation(value = "Find a user by id")
	public ResponseEntity<UserDto> findById(@ApiParam("User id") Long id);

	@ApiOperation(value = "Find all users")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", value = "Results of the page sent", defaultValue = "0"),
			@ApiImplicitParam(name = "size", dataType = "integer", paramType = "query", value = "Number of records per page", defaultValue = "0"),
			@ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query", value = "Search order property in the format: property (asc | desc)."
					+ " The ordenation is asdent by default." + " Multiple sorting criteria are used.") })
	public ResponseEntity<List<UserDto>> findAll(
			@ApiIgnore("Ignored parameters because they are already described in implicit parameters") Pageable pageable);

	@ApiOperation(value = "Update user data")
	public ResponseEntity<UserDto> update(@ApiParam("User id") Long id, @ApiParam("User data") User form);

	@ApiOperation(value = "Delete a user by id")
	public ResponseEntity<?> delete(@ApiParam("User id") Long id);
}
