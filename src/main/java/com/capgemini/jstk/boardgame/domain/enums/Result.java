package com.capgemini.jstk.boardgame.domain.enums;

public enum Result {
	
	WIN(10),
	DRAW(5),
	LOST(0),
	FIRST_PLACE(10),
	SECOND_PLACE(6),
	THIRD_PLACE(3);
	
	private final int points;
	
	private Result(int value) {
		this.points = value;
	}

	public int getPoints() {
		return points;
	}
}
