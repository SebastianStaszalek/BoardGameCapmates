package com.capgemini.jstk.boardgame.dto;

import java.time.Instant;
import java.util.Set;

import com.capgemini.jstk.boardgame.domain.GameEntity;
import com.capgemini.jstk.boardgame.domain.UserEntity;
import com.capgemini.jstk.boardgame.domain.enums.Result;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GamesHistoryTO {
	
	private GameEntity gamePlayed;
	private Instant dateOfPlay;
	private Result result;
	private int pointsEarned;
	private Set<UserEntity> players;

}
