package com.capgemini.jstk.boardgame.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.capgemini.jstk.boardgame.domain.GamesHistoryEntity;
import com.capgemini.jstk.boardgame.dto.GamesHistoryTO;

@Component
public class GamesHistoryMapper {
	
	public GamesHistoryTO map(GamesHistoryEntity gamesHistoryEntity) {
		if (gamesHistoryEntity != null) {
			return GamesHistoryTO.builder()
					.gamePlayed(gamesHistoryEntity.getGamePlayed())
					.dateOfPlay(gamesHistoryEntity.getDateOfPlay())
					.result(gamesHistoryEntity.getResult())
					.pointsEarned(gamesHistoryEntity.getPointsEarned())
					.players(gamesHistoryEntity.getPlayers())
					.build();
		}
		return null;
	}
	
	public GamesHistoryEntity map(GamesHistoryTO gamesHistoryTO) {
		if (gamesHistoryTO != null) {
			return GamesHistoryEntity.builder()
					.gamePlayed(gamesHistoryTO.getGamePlayed())
					.dateOfPlay(gamesHistoryTO.getDateOfPlay())
					.result(gamesHistoryTO.getResult())
					.pointsEarned(gamesHistoryTO.getPointsEarned())
					.players(gamesHistoryTO.getPlayers())
					.build();
		}
		return null;
	}
	
	public List<GamesHistoryTO> map2TO(List<GamesHistoryEntity> gamesHistoryEntities) {
		return gamesHistoryEntities.stream().map(this::map).collect(Collectors.toList());
	}
	
	public List<GamesHistoryEntity> map2Entity(List<GamesHistoryTO> gamesHistoryTOs) {
		return gamesHistoryTOs.stream().map(this::map).collect(Collectors.toList());
	}
}
