package com.capgemini.jstk.boardgame.domain;

import lombok.Data;

@Data
public class Game {

	private String name;
	private String description;
	private int minimumPlayers;
	private int maximumPlayers;
}
