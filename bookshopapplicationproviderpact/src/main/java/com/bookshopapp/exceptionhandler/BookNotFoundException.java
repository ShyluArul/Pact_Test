package com.bookshopapp.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class BookNotFoundException extends RuntimeException {
	public BookNotFoundException(String exception) {
		super(exception);
	}

	private static final long serialVersionUID = 1L;
}
