package com.capgemini.jstk.boardgame.dto;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AvailibilityTimeTO {

	private Long id;
	private Instant from;
	private Instant to;
	private String comment;
}
