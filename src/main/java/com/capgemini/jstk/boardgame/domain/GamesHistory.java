package com.capgemini.jstk.boardgame.domain;

import java.time.LocalDate;
import java.util.Set;

import com.capgemini.jstk.boardgame.domain.enums.Result;

import lombok.Data;

@Data
public class GamesHistory {
	
	private Game gamePlayed;
	private LocalDate dateOfPlay;
	private Result result;
	private int pointsEarned;
	private Set<User> players;

}
