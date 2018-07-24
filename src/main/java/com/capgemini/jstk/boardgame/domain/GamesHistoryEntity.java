package com.capgemini.jstk.boardgame.domain;

import java.time.LocalDate;
import java.util.Set;

import com.capgemini.jstk.boardgame.domain.enums.Result;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GamesHistoryEntity {
	
	private GameEntity gamePlayed;
	private LocalDate dateOfPlay;
	private Result result;
	private int pointsEarned;
	private Set<UserEntity> players;

}
