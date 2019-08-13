package com.ing.bank.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(FundTransferException.class)
	public ResponseEntity<ErrorResponse> handleUserException(FundTransferException exception)
	{
		ErrorResponse errorResponse = new ErrorResponse(exception.getMessage(), new Date());
		return new ResponseEntity<ErrorResponse>(errorResponse,HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(UserAccountException.class)
	public ResponseEntity<ErrorResponse> handleUserDataException(UserAccountException userAccountexception)
	{
		ErrorResponse errorResponse = new ErrorResponse(userAccountexception.getMessage(), new Date());
		return new ResponseEntity<ErrorResponse>(errorResponse,HttpStatus.NOT_FOUND);
	}
	
}
