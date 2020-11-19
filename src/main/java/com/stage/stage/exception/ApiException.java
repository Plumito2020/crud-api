package com.stage.stage.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ApiException {

	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private String customMsg ;
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private HttpStatus httpStatus ;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime timeStamp;
	
	
	public ApiException(String customMsg, HttpStatus httpStatus , LocalDateTime timeStamp) {
		super();
		this.customMsg = customMsg;
		this.httpStatus = httpStatus;
		this.timeStamp = timeStamp;
		
	}
	
	
	
}
