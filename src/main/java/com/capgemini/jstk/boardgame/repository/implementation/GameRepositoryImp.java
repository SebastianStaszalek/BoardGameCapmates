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
	
	List<GameEntity> gamesList = new ArrayList<>();
	
	@Override
	public void add(String name, String description, int minimumPlayers, int maximumPlayers) {
		Preconditions.checkNotNull(name, NAME_IS_NULL);
		Preconditions.checkNotNull(minimumPlayers, MINIMUM_IS_NULL);
		Preconditions.checkNotNull(maximumPlayers, MAXIMUM_IS_NULL);
		
		GameEntity gameEntity = GameEntity.builder()
				.name(name)
				.description(description)
				.minimumPlayers(minimumPlayers)
				.maximumPlayers(maximumPlayers)
				.build();
		
		this.gamesList.add(gameEntity);
	}
	
	@Override
	public void update(String name, String description, int minimumPlayers, int maximumPlayers) {
		Preconditions.checkNotNull(name, NAME_IS_NULL);
		
		GameEntity gameToUpdate = findByName(name);
		gameToUpdate.setName(name);
		gameToUpdate.setDescription(description);
		gameToUpdate.setMinimumPlayers(minimumPlayers);
		gameToUpdate.setMaximumPlayers(maximumPlayers);
		
	}

	@Override
	public GameEntity findByName(String name) {
		Preconditions.checkNotNull(name, NAME_IS_NULL);
		
		return gamesList.stream()
				.filter(n -> name.equals(n.getName()))
				.findAny()
				.orElse(null);
	}

	@Override
	public void delete(String name) {
		Preconditions.checkNotNull(name, NAME_IS_NULL);
		
		this.gamesList.remove(Optional.ofNullable(findByName(name)));
		
	}

}
