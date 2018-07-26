package com.capgemini.jstk.boardgame.domain.errors;

public class GameDuplicateException extends RuntimeException {

	private static final long serialVersionUID = -891194629449885802L;

	public GameDuplicateException(String message) {
		super(message);
	}
	
	

}
