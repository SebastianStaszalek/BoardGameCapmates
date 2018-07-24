package com.capgemini.jstk.boardgame.repository.implementation;

import java.time.LocalDateTime;
import java.util.List;

import javax.naming.directory.InvalidAttributesException;

import org.springframework.stereotype.Repository;

import com.capgemini.jstk.boardgame.domain.AvailibilityTimeEntity;
import com.capgemini.jstk.boardgame.domain.UserEntity;
import com.capgemini.jstk.boardgame.repository.AvailibilityTimeRepository;

@Repository
public class AvailibilityTimeRepositoryImp implements AvailibilityTimeRepository {

	@Override
	public void add(LocalDateTime from, LocalDateTime to) {
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
	public AvailibilityTimeEntity findByTimePeriod(UserEntity user, LocalDateTime from, LocalDateTime to) {
		if (from != null && to != null) {
			List<AvailibilityTimeEntity> availibilityTime = findAll(user);
			for (AvailibilityTimeEntity time : availibilityTime) {
				//TODO: dobrze sprawdzic okresy dostepnosci!
				if (time.getFrom().isAfter(from) && time.getTo().isBefore(to)) {
					return time;
				}
			}
		}
		return null;

		// long numOfDaysBetween = ChronoUnit.DAYS.between(from, to);
		// return IntStream.iterate(0, i -> i + 1)
		// .limit(numOfDaysBetween)
		// .mapToObj(i -> from.plusDays(i))
		// .collect(Collectors.toList());
	}

	@Override
	public void update(AvailibilityTimeEntity time, LocalDateTime from, LocalDateTime to, String comment) {
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
