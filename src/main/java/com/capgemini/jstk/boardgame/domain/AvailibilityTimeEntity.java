package com.capgemini.jstk.boardgame.domain;

import java.time.Instant;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvailibilityTimeEntity {
	
	private Instant from;
	private Instant to;
	private String comment;

}
