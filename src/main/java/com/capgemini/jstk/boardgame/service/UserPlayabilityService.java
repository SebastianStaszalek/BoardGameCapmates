package com.capgemini.jstk.boardgame.service;

import java.util.List;

import com.capgemini.jstk.boardgame.dto.AvailibilityTimeTO;
import com.capgemini.jstk.boardgame.dto.UserTO;

public interface UserPlayabilityService {
	
	List<AvailibilityTimeTO> addAvailibilityTimeForUser(UserTO user, AvailibilityTimeTO availibilityTime);
	
	List<AvailibilityTimeTO> updateAvailibilityTime(UserTO user, AvailibilityTimeTO availibilityTimeTO);
}
