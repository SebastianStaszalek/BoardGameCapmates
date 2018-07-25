package com.capgemini.jstk.boardgame.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GameTO {

	private String name;
	private String description;
	private int minimumPlayers;
	private int maximumPlayers;
}
