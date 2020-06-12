package com.bookshopapp.exceptionhandler;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@SuppressWarnings({ "unchecked", "rawtypes" })
@ControllerAdvice
@RestController
public class CustomExceptionHandler {

	@ExceptionHandler(BookNotFoundException.class)
	public final ResponseEntity<Object> handleUserNotFoundException(BookNotFoundException ex, WebRequest request) {
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		ErrorResponse error = new ErrorResponse("Datas Not Found", details);
		return new ResponseEntity(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
		ErrorResponseUrl error = new ErrorResponseUrl("You are entering wrong URL");
		return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
	}

}
