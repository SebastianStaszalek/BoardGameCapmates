package com.capgemini.jstk.boardgame.domain;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class AvailibilityTime {
	
	private LocalDateTime from;
	private LocalDateTime to;
	private String comment;

}
