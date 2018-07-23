package com.capgemini.jstk.boardgame.domain.enums;

public enum Level {
	
	SAMWELL_TARLY(1, 0, 0),
	LANCEL_LANISTER(2, 100, 10),
	PODRICK_PAYNE(3, 200, 20),
	JORAH_MORMONT(4, 400, 40),
	ROBB_STARK(5, 800, 80),
	OBERYN_MARTELL(6, 1600, 160),
	DAARIO_NAHARIS(7, 3200, 320),
	KHAL_DROGO(8, 6400, 640),
	NIGTH_KING(9, 12800, 1280);
	
	private final int value;
	private final int pointsRequired;
    private final int gamesRequired;
    
	private Level(int value, int pointsRequired, int gamesRequired) {
		this.value = value;
		this.pointsRequired = pointsRequired;
		this.gamesRequired = gamesRequired;
	}

	public int getValue() {
		return value;
	}

	public int getPointsRequired() {
		return pointsRequired;
	}

	public int getGamesRequired() {
		return gamesRequired;
	}
}
