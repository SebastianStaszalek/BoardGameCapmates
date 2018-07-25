package com.capgemini.jstk.boardgame.dto;

import java.util.List;
import java.util.Set;

import com.capgemini.jstk.boardgame.domain.AvailibilityTimeEntity;
import com.capgemini.jstk.boardgame.domain.GameEntity;
import com.capgemini.jstk.boardgame.domain.GamesHistoryEntity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserTO {

	private String eMail;
	private String password;
	private String firstName;
	private String lastName;
	private String motto;
	
	private List<GamesHistoryEntity> gamesHistory;
	private Set<GameEntity> gamesCollection;
	private List<AvailibilityTimeEntity> availibilityTime;

}
