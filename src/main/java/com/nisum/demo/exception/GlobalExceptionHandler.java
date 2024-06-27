package com.nisum.demo.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(value = { Exception.class })
    public ResponseEntity<Object> handleException(Exception ex) {
        LOGGER.error("Exception: ",ex);
        return new ResponseEntity<Object>(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

	@ExceptionHandler(EmailExistException.class)
    public ResponseEntity<ErrorResponse> handleProductNotFoundException(EmailExistException ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.CONFLICT.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

}
