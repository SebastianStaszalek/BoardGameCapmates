package com.capgemini.jstk.boardgame.domain;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Set;

import lombok.Data;

@Data
public class ChallengeEntity {

	private UserEntity from;
	private Set<UserEntity> to;
	private GameEntity gameToBeChallenged;
	private Instant dateOfChallenge;
}
