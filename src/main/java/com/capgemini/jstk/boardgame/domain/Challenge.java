package com.capgemini.jstk.boardgame.domain;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Set;

import lombok.Data;

@Data
public class Challenge {

	private User from;
	private Set<User> to;
	private Game gameToBeChallenged;
	private Instant dateOfChallenge;
}
