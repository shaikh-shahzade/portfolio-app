package com.portfolio.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.portfolio.payload.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHanlder {

	@ExceptionHandler( value =ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourceNotFoundException(ResourceNotFoundException ex)
	{
		ApiResponse res = new ApiResponse(ex.getMessage() , false);
		return new ResponseEntity<ApiResponse>(res , HttpStatus.NOT_FOUND);
		
	}
}
