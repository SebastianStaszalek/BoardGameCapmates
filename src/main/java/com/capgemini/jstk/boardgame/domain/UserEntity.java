package com.capgemini.jstk.boardgame.domain;


import java.util.List;
import java.util.Set;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserEntity {
	
	private String eMail;
	private String password;
	private String firstName;
	private String lastName;
	private String motto;
	
	private List<GamesHistoryEntity> gamesHistory;
	private Set<GameEntity> gamesCollection;
	private List<AvailibilityTimeEntity> availibilityTime;

}
