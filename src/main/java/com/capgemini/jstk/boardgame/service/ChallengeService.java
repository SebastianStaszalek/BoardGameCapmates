package com.capgemini.jstk.boardgame.service;

import java.time.Instant;
import java.util.Set;

import com.capgemini.jstk.boardgame.dto.ChallengeTO;
import com.capgemini.jstk.boardgame.dto.GameTO;
import com.capgemini.jstk.boardgame.dto.UserTO;

public interface ChallengeService {
	
	ChallengeTO createChallenge(UserTO from, Set<UserTO> to, GameTO game, Instant date);
}
