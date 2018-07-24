package com.capgemini.jstk.boardgame.repository;

import com.capgemini.jstk.boardgame.domain.GameEntity;

public interface GameRepository {
	
	void add(String name, String description, int minimumPlayers, int maximumPlayers);
	
	void update(String name, String description, int minimumPlayers, int maximumPlayers);
	
	GameEntity findByName(String name);
	
	void delete(String name);

}
