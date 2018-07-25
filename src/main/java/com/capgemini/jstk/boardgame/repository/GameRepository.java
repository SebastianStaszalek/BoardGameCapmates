package com.capgemini.jstk.boardgame.repository;

import com.capgemini.jstk.boardgame.domain.GameEntity;

public interface GameRepository {
	
	void add(GameEntity game);
	
	void update(GameEntity game);
	
	GameEntity findByName(String name);
	
	void delete(String name);

}
