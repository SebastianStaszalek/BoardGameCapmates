package com.capgemini.jstk.boardgame.domain;

import java.time.LocalDate;
import java.util.Set;

import com.capgemini.jstk.boardgame.domain.enums.Result;

import lombok.Data;

@Data
public class GamesHistoryEntity {
	
	private GameEntity gamePlayed;
	private LocalDate dateOfPlay;
	private Result result;
	private int pointsEarned;
	private Set<UserEntity> players;

}
