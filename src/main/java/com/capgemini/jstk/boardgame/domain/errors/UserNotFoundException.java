package com.capgemini.jstk.boardgame.domain.errors;


public class UserNotFoundException extends RuntimeException{

	private static final long serialVersionUID = -6512110281137122864L;

	public UserNotFoundException() {
		super("User not found");
	}

	public UserNotFoundException(String message) {
		super(message);
	}

	
}
