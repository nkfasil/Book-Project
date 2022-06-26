package com.book.bookpricing.exception;

import java.time.LocalDateTime;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionBookHandler {

	@ExceptionHandler(BookNotFoundException.class)
	public ResponseEntity<ErrorMessage> handleEx(BookNotFoundException e){
		return new ResponseEntity<ErrorMessage>(
				new ErrorMessage(
						e.getMessage(), 
						LocalDateTime.now(), 
						e.getClass().toString())
				, HttpStatus.OK);
	}
	
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ResponseEntity<ErrorMessage> handleExEmpty(BookNotFoundException e){
		return new ResponseEntity<ErrorMessage>(
				new ErrorMessage(
						e.getMessage(), 
						LocalDateTime.now(), 
						e.getClass().toString())
				, HttpStatus.OK);
	}
}
