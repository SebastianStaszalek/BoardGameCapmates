package com.capgemini.jstk.boardgame.repository;

import java.time.LocalDateTime;
import java.util.List;

import com.capgemini.jstk.boardgame.domain.AvailibilityTimeEntity;
import com.capgemini.jstk.boardgame.domain.UserEntity;

public interface AvailibilityTimeRepository {
	
	void add(LocalDateTime from, LocalDateTime to);
	List<AvailibilityTimeEntity> findAll(UserEntity user);
	AvailibilityTimeEntity findByTimePeriod(UserEntity user, LocalDateTime from, LocalDateTime to);
	void update(AvailibilityTimeEntity time, LocalDateTime from, LocalDateTime to, String comment);
	void delete(AvailibilityTimeEntity time, String comment);
	
	
	//AvailibilityTime findByDate(LocalDateTime from, LocalDateTime to);
}
