package com.capgemini.jstk.boardgame.repository.implementation;

import java.time.LocalDateTime;
import java.util.List;

import javax.naming.directory.InvalidAttributesException;

import org.springframework.stereotype.Repository;

import com.capgemini.jstk.boardgame.domain.AvailibilityTime;
import com.capgemini.jstk.boardgame.domain.User;
import com.capgemini.jstk.boardgame.repository.AvailibilityTimeRepository;

@Repository
public class AvailibilityTimeRepositoryImp implements AvailibilityTimeRepository {

	@Override
	public void add(LocalDateTime from, LocalDateTime to) {
		if (from != null && to != null) {
			AvailibilityTime availibilityTime = new AvailibilityTime();
			availibilityTime.setFrom(from);
			availibilityTime.setTo(to);
		}
	}

	@Override
	public List<AvailibilityTime> findAll(User user) {
		List<AvailibilityTime> timeList = user.getAvailibilityTime();
		return timeList;
	}

	@Override
	public AvailibilityTime findByTimePeriod(User user, LocalDateTime from, LocalDateTime to) {
		if (from != null && to != null) {
			List<AvailibilityTime> availibilityTime = findAll(user);
			for (AvailibilityTime time : availibilityTime) {
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
	public void update(AvailibilityTime time, LocalDateTime from, LocalDateTime to, String comment) {
		time.setFrom(from);
		time.setTo(to);
		time.setComment(comment);
	}

	@Override
	public void delete(AvailibilityTime time, String comment) {
		time.setFrom(null);
		time.setTo(null);
		time.setComment(comment);
	}

	private void checkIfNuLL(LocalDateTime from, LocalDateTime to) throws InvalidAttributesException {
		if (from == null || to == null) {
			throw new InvalidAttributesException("period time cannot be empty");
		}
	}
}
