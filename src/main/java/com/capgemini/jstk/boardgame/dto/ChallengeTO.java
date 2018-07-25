package com.capgemini.jstk.boardgame.dto;

import java.time.Instant;
import java.util.Set;

import com.capgemini.jstk.boardgame.domain.GameEntity;
import com.capgemini.jstk.boardgame.domain.UserEntity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChallengeTO {

	private UserEntity from;
	private Set<UserEntity> to;
	private GameEntity gameToBeChallenged;
	private Instant dateOfChallenge;
}
