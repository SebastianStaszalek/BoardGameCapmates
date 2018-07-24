package com.capgemini.jstk.boardgame.repository.implementation;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.capgemini.jstk.boardgame.domain.ChallengeEntity;
import com.capgemini.jstk.boardgame.domain.GameEntity;
import com.capgemini.jstk.boardgame.domain.UserEntity;
import com.capgemini.jstk.boardgame.repository.ChallengeRepository;

public class ChallengeRepositoryImp implements ChallengeRepository {

	List<ChallengeEntity> challengesList = new ArrayList<>();
	
	@Override
	public void createChallenge(UserEntity from, Set<UserEntity> to, GameEntity game, Instant date) {
		ChallengeEntity newChallenge = ChallengeEntity.builder()
				.from(from)
				.to(to)
				.gameToBeChallenged(game)
				.dateOfChallenge(date)
				.build();
		
		this.challengesList.add(newChallenge);
	}

}
