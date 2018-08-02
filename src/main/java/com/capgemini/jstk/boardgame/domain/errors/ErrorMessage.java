package com.capgemini.jstk.boardgame.domain.errors;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorMessage {
	
	private Date date;
	private String error;
	
	public ErrorMessage(String error) {
		this.date = new Date();
		this.error = error;
	}
	
}
