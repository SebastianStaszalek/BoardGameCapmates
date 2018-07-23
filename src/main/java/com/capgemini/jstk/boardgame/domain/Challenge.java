package com.capgemini.jstk.boardgame.domain;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Challenge {

	private User from;
	private User to;
	private Game gameToBeChallenged;
	private LocalDateTime dateOfChallenge;
}
