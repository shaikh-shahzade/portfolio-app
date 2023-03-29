package com.portfolio.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
	
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String , String>> notValid(MethodArgumentNotValidException ex)
	{
		Map<String, String> map = new HashMap<String,String>();
		ex.getBindingResult().getAllErrors().forEach((error)->{
			map.put(((FieldError)error).getField(), error.getDefaultMessage());
		});
		return new ResponseEntity<Map<String,String>>(map , HttpStatus.BAD_REQUEST);
	}
	
}
