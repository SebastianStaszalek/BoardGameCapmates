package com.capgemini.jstk.boardgame.service.implementation;

import java.time.Instant;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.jstk.boardgame.domain.ChallengeEntity;
import com.capgemini.jstk.boardgame.domain.GameEntity;
import com.capgemini.jstk.boardgame.domain.UserEntity;
import com.capgemini.jstk.boardgame.dto.ChallengeTO;
import com.capgemini.jstk.boardgame.dto.GameTO;
import com.capgemini.jstk.boardgame.dto.UserTO;
import com.capgemini.jstk.boardgame.mapper.ChallengeMapper;
import com.capgemini.jstk.boardgame.mapper.GameMapper;
import com.capgemini.jstk.boardgame.mapper.UserMapper;
import com.capgemini.jstk.boardgame.repository.ChallengeRepository;
import com.capgemini.jstk.boardgame.service.ChallengeService;

@Service
public class ChallengeServiceImp implements ChallengeService{
	
	ChallengeRepository challengeRepository;
	ChallengeMapper challengeMapper;
	
	UserMapper userMapper;
	GameMapper gameMapper;
	
	@Autowired
	public ChallengeServiceImp(ChallengeRepository challengeRepository, ChallengeMapper challengeMapper,
			UserMapper userMapper, GameMapper gameMapper) {
		super();
		this.challengeRepository = challengeRepository;
		this.challengeMapper = challengeMapper;
		this.userMapper = userMapper;
		this.gameMapper = gameMapper;
	}


	@Override
	public ChallengeTO createChallenge(UserTO from, Set<UserTO> to, GameTO game, Instant date) {
		UserEntity userFrom = userMapper.map(from);
		Set<UserEntity> usersTo = userMapper.map2SetEntity(to);
		GameEntity gameEntity = gameMapper.map(game);
		
		ChallengeEntity newChallenge = challengeRepository.createChallenge(userFrom, usersTo, gameEntity, date);
		
		return challengeMapper.map(newChallenge);
	}

}
