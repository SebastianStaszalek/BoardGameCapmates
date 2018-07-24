package com.capgemini.jstk.boardgame.repository;

import java.time.LocalDateTime;
import java.util.List;

import com.capgemini.jstk.boardgame.domain.AvailibilityTime;
import com.capgemini.jstk.boardgame.domain.User;

public interface AvailibilityTimeRepository {
	
	void add(LocalDateTime from, LocalDateTime to);
	List<AvailibilityTime> findAll(User user);
	AvailibilityTime findByTimePeriod(User user, LocalDateTime from, LocalDateTime to);
	void update(AvailibilityTime time, LocalDateTime from, LocalDateTime to, String comment);
	void delete(AvailibilityTime time, String comment);
	
	
	//AvailibilityTime findByDate(LocalDateTime from, LocalDateTime to);
}
