package com.dany.order.management.commons.exceptions;

import com.dany.order.management.commons.APIResponse;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;


@ControllerAdvice
public class ExceptionHandlerAdvice {
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<APIResponse> conflict(DataIntegrityViolationException ex){
		String message = getMostSpecificMessage(ex);
		
		return new ResponseEntity<>(new APIResponse(false, message), HttpStatus.CONFLICT);
	}
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<APIResponse> methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex){
		ex.printStackTrace();
		
		String message = ex.getMessage();
		return new ResponseEntity<>(new APIResponse(false, message), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@ExceptionHandler(Exception.class)
	public ResponseEntity<APIResponse> unhandledExceptions(Exception ex){
		String message = NestedExceptionUtils.getMostSpecificCause(ex).getMessage();
		
		ex.printStackTrace();
		
		return new ResponseEntity<>(new APIResponse(false, message), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@ExceptionHandler(EntityNotExistException.class)
	public ResponseEntity<APIResponse> entityNotExistException(EntityNotExistException ex) {
		ex.printStackTrace();
		String message = ex.getMessage();

		return new ResponseEntity<>(new APIResponse(false, message), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	private String getMostSpecificMessage(DataIntegrityViolationException ex) {
		String message = NestedExceptionUtils.getMostSpecificCause(ex).getMessage();
		
		if(message.contains("Detail:")) {
			message = message.substring(message.indexOf("Detail:")+"Detail:".length());
		}
		
		return message;
	}
}
