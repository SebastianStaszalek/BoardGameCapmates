package com.capgemini.jstk.boardgame.repository;

import java.time.Instant;
import java.util.Set;

import com.capgemini.jstk.boardgame.domain.GameEntity;
import com.capgemini.jstk.boardgame.domain.UserEntity;

public interface ChallengeRepository {
	
	//TODO: przekazywac tylko e-mail uzytkownika czy calego User?
	//TODO: w kilku przypadkach to samo pytanie?
	//TODO: czy tworzyc metody ktorych narazie nie wykorzystamy? np. update
	void createChallenge(UserEntity from, Set<UserEntity> to, GameEntity game, Instant date);
	
}
