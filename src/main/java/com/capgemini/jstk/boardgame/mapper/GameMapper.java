package com.capgemini.jstk.boardgame.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.capgemini.jstk.boardgame.domain.GameEntity;
import com.capgemini.jstk.boardgame.dto.GameTO;

public class GameMapper {
	
	public static GameTO map(GameEntity gameEntity) {
		if (gameEntity != null) {
			return GameTO.builder()
					.name(gameEntity.getName())
					.description(gameEntity.getDescription())
					.minimumPlayers(gameEntity.getMinimumPlayers())
					.maximumPlayers(gameEntity.getMaximumPlayers())
					.build();
		}
		return null;
	}
	
	public static GameEntity map (GameTO gameTO) {
		if (gameTO != null) {
			return GameEntity.builder()
					.name(gameTO.getName())
					.description(gameTO.getDescription())
					.minimumPlayers(gameTO.getMinimumPlayers())
					.maximumPlayers(gameTO.getMaximumPlayers())
					.build();
		}
		return null;
	}
	
	public static List<GameTO> map2TO(List<GameEntity> gameEnities) {
		return gameEnities.stream().map(GameMapper::map).collect(Collectors.toList());
	}
	
	public static List<GameEntity> map2Entity(List<GameTO> gameEnities) {
		return gameEnities.stream().map(GameMapper::map).collect(Collectors.toList());
	}
}
