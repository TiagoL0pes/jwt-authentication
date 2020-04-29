package com.authentication.util.exceptions;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {

	private LocalDateTime dateTime;
	private String message;

	public ErrorResponse(String message) {
		this.dateTime = LocalDateTime.now();
		this.message = message;
	}

}
