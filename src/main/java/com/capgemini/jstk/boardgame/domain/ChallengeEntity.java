package com.capgemini.jstk.boardgame.domain;

import java.time.Instant;
import java.util.Set;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChallengeEntity {

	private UserEntity from;
	private Set<UserEntity> to;
	private GameEntity gameToBeChallenged;
	private Instant dateOfChallenge;
}
