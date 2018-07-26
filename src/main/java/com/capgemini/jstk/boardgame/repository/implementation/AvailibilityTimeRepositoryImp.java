package com.capgemini.jstk.boardgame.repository.implementation;

import java.time.Instant;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.capgemini.jstk.boardgame.domain.AvailibilityTimeEntity;
import com.capgemini.jstk.boardgame.domain.UserEntity;
import com.capgemini.jstk.boardgame.repository.AvailibilityTimeRepository;

@Repository
public class AvailibilityTimeRepositoryImp implements AvailibilityTimeRepository {

	@Override
	public void add(Instant from, Instant to) {
		if (from != null && to != null) {
			AvailibilityTimeEntity availibilityTime = new AvailibilityTimeEntity();
			availibilityTime.setFrom(from);
			availibilityTime.setTo(to);
		}
	}

	@Override
	public List<AvailibilityTimeEntity> findAll(UserEntity user) {
		List<AvailibilityTimeEntity> timeList = user.getAvailibilityTime();
		return timeList;
	}

	@Override
	public AvailibilityTimeEntity findByTimePeriod(UserEntity user, Instant from, Instant to) {
		if (from != null && to != null) {
			List<AvailibilityTimeEntity> availibilityTime = findAll(user);
			for (AvailibilityTimeEntity time : availibilityTime) {
				if (time.getFrom().isAfter(from) && time.getTo().isBefore(to)) {
					return time;
				}
			}
		}
		return null;
	}

	@Override
	public void update(AvailibilityTimeEntity time, Instant from, Instant to, String comment) {
		time.setFrom(from);
		time.setTo(to);
		time.setComment(comment);
	}

	@Override
	public void delete(AvailibilityTimeEntity time, String comment) {
		time.setFrom(null);
		time.setTo(null);
		time.setComment(comment);
	}
}
