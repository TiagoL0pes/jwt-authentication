package com.authentication.util.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.authentication.util.exceptions.BadRequestException;
import com.authentication.util.exceptions.ErrorResponse;

@ControllerAdvice
public class BadRequestExceptionHandler {

	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<ErrorResponse> handleNotFoundExceptions(BadRequestException e) {
		ErrorResponse error = new ErrorResponse(e.getMessage());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
}
