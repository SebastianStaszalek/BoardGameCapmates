package com.capgemini.jstk.boardgame.service;

import java.util.List;

import com.capgemini.jstk.boardgame.dto.AvailibilityTimeTO;
import com.capgemini.jstk.boardgame.dto.GameTO;
import com.capgemini.jstk.boardgame.dto.UserTO;

public interface UserPlayabilityService {
	
	List<AvailibilityTimeTO> addAvailibilityTimeForUser(UserTO userTO, AvailibilityTimeTO timeTO);
	
	List<AvailibilityTimeTO> updateAvailibilityTime(UserTO userTO, AvailibilityTimeTO timeTO);
	
	List<UserTO> getUsersWithSimilarAvailibilityTime(AvailibilityTimeTO timeTO, GameTO gameTO);
}
