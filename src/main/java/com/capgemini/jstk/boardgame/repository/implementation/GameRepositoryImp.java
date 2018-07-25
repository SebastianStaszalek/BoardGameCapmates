package com.capgemini.jstk.boardgame.repository.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.util.Preconditions;

import com.capgemini.jstk.boardgame.domain.GameEntity;
import com.capgemini.jstk.boardgame.repository.GameRepository;


public class GameRepositoryImp implements GameRepository {

	private final static String NAME_IS_NULL = "The name should not be empty";
	private final static String MINIMUM_IS_NULL = "The minimum number of players should not be empty";
	private final static String MAXIMUM_IS_NULL = "The maximum number of players should not be empty";
	private final static String NAME_DUPLICATE = "This name of the game already exists ";
	private final static String GAME_NOT_FOUND = "Game not found";
	
	List<GameEntity> gamesList = new ArrayList<>();
	
	@Override
	public void add(GameEntity game) {
		Preconditions.checkNotNull(game.getName(), NAME_IS_NULL);
		Preconditions.checkNotNull(game.getMinimumPlayers(), MINIMUM_IS_NULL);
		Preconditions.checkNotNull(game.getMaximumPlayers(), MAXIMUM_IS_NULL);
		
		if (gamesList.stream().anyMatch(n -> n.getName().equals(game.getName()))) {
			throw new RuntimeException(NAME_DUPLICATE);
		}
		
		this.gamesList.add(game);
	}
	
	@Override
	public void update(GameEntity game) {
		Preconditions.checkNotNull(game.getName(), NAME_IS_NULL);
		
		GameEntity gameToUpdate = findByName(game.getName());
		gameToUpdate.setName(game.getName());
		gameToUpdate.setDescription(game.getDescription());
		gameToUpdate.setMinimumPlayers(game.getMinimumPlayers());
		gameToUpdate.setMaximumPlayers(game.getMaximumPlayers());
		
	}
	
	@Override
	public GameEntity findByName(String name) {
		Preconditions.checkNotNull(name, NAME_IS_NULL);
		
		return gamesList.stream()
				.filter(n -> name.equals(n.getName()))
				.findAny()
				.orElseThrow(() -> new RuntimeException(GAME_NOT_FOUND));
	}

	@Override
	public void delete(String name) {
		Preconditions.checkNotNull(name, NAME_IS_NULL);
		
		this.gamesList.remove(Optional.ofNullable(findByName(name)));
	}

}
