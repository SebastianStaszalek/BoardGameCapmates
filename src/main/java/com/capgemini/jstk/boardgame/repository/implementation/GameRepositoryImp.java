package com.capgemini.jstk.boardgame.repository.implementation;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.assertj.core.util.Preconditions;
import org.springframework.stereotype.Repository;

import com.capgemini.jstk.boardgame.domain.GameEntity;
import com.capgemini.jstk.boardgame.repository.GameRepository;

@Repository
public class GameRepositoryImp implements GameRepository {

	private final static String NAME_IS_NULL = "The name should not be empty";
	private final static String MINIMUM_IS_NULL = "The minimum number of players should not be empty";
	private final static String MAXIMUM_IS_NULL = "The maximum number of players should not be empty";
	private final static String NAME_DUPLICATE = "This name of the game already exists ";
	private final static String GAME_NOT_FOUND = "Game not found";
	
	List<GameEntity> gamesList = new ArrayList<>();
	
	@PostConstruct
	public void initialize() {
		gamesList.add(GameEntity.builder().name("5seconds").minimumPlayers(2).maximumPlayers(8).description("Think fast!").build());
		gamesList.add(GameEntity.builder().name("Ships").minimumPlayers(2).maximumPlayers(2).description("Ships war").build());
		gamesList.add(GameEntity.builder().name("Monopoly").minimumPlayers(2).maximumPlayers(5).description("Manage your country").build());
		gamesList.add(GameEntity.builder().name("Chess").minimumPlayers(2).maximumPlayers(2).description("Classic game").build());
		gamesList.add(GameEntity.builder().name("Duplo").minimumPlayers(2).maximumPlayers(5).build());
	}
	
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
		
		this.gamesList.remove(name);
	}

}
