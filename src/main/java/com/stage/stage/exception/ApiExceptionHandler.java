package com.stage.stage.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {

	// Value param is used to specify which type of exceptions i'm catching 
	@ExceptionHandler(value = ApiRequestException.class)
	public ResponseEntity<Object> handleApiException(ApiRequestException e){
		
		ApiException apiException = new ApiException(e.getMessage(), HttpStatus.BAD_REQUEST , LocalDateTime.now() );
	
		return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST) ;
	}
	
}
