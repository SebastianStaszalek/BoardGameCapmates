package com.capgemini.jstk.boardgame.domain;


import java.util.List;
import java.util.Set;

import lombok.Data;

@Data
public class User {
	
	private String nickName;
	private String firstName;
	private String lastName;
	private String eMail;
	private String password;
	private String motto;
	
	private GamesHistory gamesHistory;
	private Set<Game> gamesCollection;
	private List<AvailibilityTime> availibilityTime;
	

}
