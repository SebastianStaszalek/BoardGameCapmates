package com.capgemini.jstk.boardgame.repository;

import java.time.Instant;
import java.util.List;

import com.capgemini.jstk.boardgame.domain.AvailibilityTimeEntity;
import com.capgemini.jstk.boardgame.domain.UserEntity;

public interface AvailibilityTimeRepository {
	
	void add(Instant from, Instant to);
	
	List<AvailibilityTimeEntity> findAll(UserEntity user);
	
	AvailibilityTimeEntity findByTimePeriod(UserEntity user, Instant from, Instant to);
	
	void update(AvailibilityTimeEntity time, Instant from, Instant to, String comment);
	
	void delete(AvailibilityTimeEntity time, String comment);
	
}
