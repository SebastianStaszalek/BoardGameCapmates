package com.capgemini.jstk.boardgame.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.capgemini.jstk.boardgame.domain.GameEntity;
import com.capgemini.jstk.boardgame.dto.GameTO;

@Component
public class GameMapper {
	
	public GameTO map(GameEntity gameEntity) {
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
	
	public GameEntity map(GameTO gameTO) {
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
	
	public List<GameTO> map2TO(List<GameEntity> gameEnities) {
		return gameEnities.stream().map(this::map).collect(Collectors.toList());
	}
	
	public List<GameEntity> map2Entity(List<GameTO> gameTOs) {
		return gameTOs.stream().map(this::map).collect(Collectors.toList());
	}
}
