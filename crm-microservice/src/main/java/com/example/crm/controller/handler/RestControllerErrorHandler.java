package com.example.crm.controller.handler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.crm.application.business.exception.CustomerAlreadyExistException;
import com.example.crm.application.business.exception.CustomerNotFoundException;
import com.example.crm.dto.error.ErrorResponse;

@RestControllerAdvice
public class RestControllerErrorHandler {
	@ExceptionHandler(CustomerNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorResponse handleCustomerNotFoundException(CustomerNotFoundException e) {
		return new ErrorResponse("Customer Rest API", e.getMessage());
	}

	@ExceptionHandler(CustomerAlreadyExistException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResponse handleCustomerAlreadyExistException(CustomerAlreadyExistException e) {
		return new ErrorResponse("Customer Rest API", e.getMessage());
	}
	
	
	@ExceptionHandler(RuntimeException.class)
	@ResponseStatus(HttpStatus.BAD_GATEWAY)
	public ErrorResponse handleRuntimeException(RuntimeException e) {
		e.printStackTrace();
		return new ErrorResponse("Application", e.getMessage());
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResponse handleConstraintViolationException(ConstraintViolationException e) {
		var message = e.getConstraintViolations()
		 .stream()
		 .map(ConstraintViolation::getMessage)
		 .toList()
		 .toString();
		return new ErrorResponse("Validation", message);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		var message = e.getAllErrors()
				.stream()
				.map(ObjectError::getDefaultMessage)
				.toList()
				.toString();
		return new ErrorResponse("Validation", message);
	}
	
}
