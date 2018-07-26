package com.capgemini.jstk.boardgame.repository;

import java.time.Instant;
import java.util.Set;

import com.capgemini.jstk.boardgame.domain.GameEntity;
import com.capgemini.jstk.boardgame.domain.GamesHistoryEntity;
import com.capgemini.jstk.boardgame.domain.UserEntity;
import com.capgemini.jstk.boardgame.domain.enums.Result;

public interface GamesHistoryRepository {

	GamesHistoryEntity createNewGameHistory(GameEntity game, Instant dateOfPlay, Result result, Set<UserEntity> players); 
}
