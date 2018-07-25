package com.capgemini.jstk.boardgame.repository.implementation;

import java.time.Instant;
import java.util.Set;

import com.capgemini.jstk.boardgame.domain.GameEntity;
import com.capgemini.jstk.boardgame.domain.GamesHistoryEntity;
import com.capgemini.jstk.boardgame.domain.UserEntity;
import com.capgemini.jstk.boardgame.domain.enums.Result;
import com.capgemini.jstk.boardgame.repository.GamesHistoryRepository;

public class GamesHistoryRepositoryImp implements GamesHistoryRepository {

	@Override
	public GamesHistoryEntity createNewGameHistory(GameEntity game, Instant dateOfPlay, Result result, Set<UserEntity> players) {
		GamesHistoryEntity newGameHistory = GamesHistoryEntity.builder()
				.gamePlayed(game)
				.dateOfPlay(dateOfPlay)
				.result(result)
				.pointsEarned(result.getPoints())
				.players(players)
				.build();
		
		return newGameHistory;
				
	}
	
	
}
