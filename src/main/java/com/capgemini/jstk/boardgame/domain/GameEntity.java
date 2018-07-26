package com.capgemini.jstk.boardgame.domain;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
public class GameEntity {

	private String name;
	private String description;
	private int minimumPlayers;
	private int maximumPlayers;
}
