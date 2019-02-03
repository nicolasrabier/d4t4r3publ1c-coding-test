package com.nicolasrabier.cabdataresearcher.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3829832363155164991L;

	public EntityNotFoundException(String message) {
		super(message);
	}
	
	
	
}
