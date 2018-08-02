package com.capgemini.jstk.boardgame.dto;

import java.util.Set;

import com.capgemini.jstk.boardgame.domain.GameEntity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor(access=AccessLevel.PACKAGE)
@AllArgsConstructor(access=AccessLevel.PACKAGE)
public class UserTO {
	
	private String eMail;
	private String password;
	private String firstName;
	private String lastName;
	private String motto;

	private Set<GameEntity> gamesCollection;
	
}
