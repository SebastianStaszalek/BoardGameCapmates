package com.capgemini.jstk.boardgame.domain.errors;

public class GameNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 918535648734657520L;

	public GameNotFoundException(String message) {
		super(message);
	}
	
	

}
