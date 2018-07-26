package com.capgemini.jstk.boardgame.domain.errors;

public class EmailDuplicateException extends RuntimeException {

	private static final long serialVersionUID = -1461518547002032361L;

	public EmailDuplicateException(String message) {
		super(message);
	}
	
	

}
