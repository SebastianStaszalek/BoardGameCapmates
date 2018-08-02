package com.capgemini.jstk.boardgame.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.capgemini.jstk.boardgame.domain.errors.EmailDuplicateException;
import com.capgemini.jstk.boardgame.domain.errors.ErrorMessage;
import com.capgemini.jstk.boardgame.domain.errors.UserNotFoundException;

@ControllerAdvice
public class GlobalErrorController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(GlobalErrorController.class);
	
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorMessage playerExceptionHandler(Exception ex) {
		
		LOGGER.error("Error in user service: ", ex);
		return new ErrorMessage(ex.getMessage());
	}

	@ExceptionHandler(UserNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorMessage userNotFoundException(Exception ex) {
		
		LOGGER.error("Error in user service: ", ex);
		return new ErrorMessage(ex.getMessage());
	}
	
	@ExceptionHandler(EmailDuplicateException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorMessage emailDuplicateException(Exception ex) {
		
		LOGGER.error("Error in user service: ", ex);
		return new ErrorMessage(ex.getMessage());
	}
	

}
