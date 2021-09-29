package com.tcs.employeedata.exception;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;


@ControllerAdvice
public class GlobalExceptionHandler  {

	// Handle specific exceptions
	@ExceptionHandler(EmployeeNotFoundException.class)
	public ResponseEntity<ErrorDetail> handleEmployeeNotFoundException(EmployeeNotFoundException exception,
			WebRequest request) {
		List<String> details = new ArrayList<>();
		details.add(exception.getLocalizedMessage());
		ErrorDetail errorDetail = new ErrorDetail(new Date(),"Employee not found", details);
		return new ResponseEntity<>(errorDetail, HttpStatus.NOT_FOUND);
	}

	// Handle global global exceptions
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handlingglobalException(Exception exception, WebRequest request) {
		List<String> details = new ArrayList<>();
		details.add(exception.getLocalizedMessage());
		ErrorDetail errorDetail = new ErrorDetail(new Date(),"server error", details);
		return new ResponseEntity<>(errorDetail, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// custom validation exception handling
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handlingValidationError(MethodArgumentNotValidException exception, WebRequest request) {
		List<String> details = new ArrayList<>();
		for(ObjectError error: exception.getBindingResult().getAllErrors()) {
		details.add(error.getDefaultMessage());
		}
		ErrorDetail errorDetail = new ErrorDetail(new Date(), "Validation error occurred", details);
		return new ResponseEntity<>(errorDetail, HttpStatus.BAD_REQUEST);
	}

}
