package com.capgemini.jstk.boardgame.repository;

import java.time.Instant;
import java.util.Set;

import com.capgemini.jstk.boardgame.domain.ChallengeEntity;
import com.capgemini.jstk.boardgame.domain.GameEntity;
import com.capgemini.jstk.boardgame.domain.UserEntity;

public interface ChallengeRepository {
	
	ChallengeEntity createChallenge(UserEntity from, Set<UserEntity> to, GameEntity game, Instant date);
	
}
