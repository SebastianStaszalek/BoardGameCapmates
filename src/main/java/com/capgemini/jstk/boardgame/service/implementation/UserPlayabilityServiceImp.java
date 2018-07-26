package com.capgemini.jstk.boardgame.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.jstk.boardgame.domain.AvailibilityTimeEntity;
import com.capgemini.jstk.boardgame.domain.GameEntity;
import com.capgemini.jstk.boardgame.domain.UserEntity;
import com.capgemini.jstk.boardgame.dto.AvailibilityTimeTO;
import com.capgemini.jstk.boardgame.dto.GameTO;
import com.capgemini.jstk.boardgame.dto.UserTO;
import com.capgemini.jstk.boardgame.mapper.AvailibilityTimeMapper;
import com.capgemini.jstk.boardgame.mapper.GameMapper;
import com.capgemini.jstk.boardgame.mapper.UserMapper;
import com.capgemini.jstk.boardgame.repository.UserRepository;
import com.capgemini.jstk.boardgame.service.UserPlayabilityService;

@Service
public class UserPlayabilityServiceImp implements UserPlayabilityService {

	private UserRepository userRepository;
	private UserMapper userMapper;

	private AvailibilityTimeMapper availibilityTimeMapper;
	
	private GameMapper gameMapper;
	
	
	@Autowired
	public UserPlayabilityServiceImp(UserRepository userRepository, UserMapper userMapper,
			AvailibilityTimeMapper availibilityTimeMapper, GameMapper gameMapper) {
		this.userRepository = userRepository;
		this.userMapper = userMapper;
		this.availibilityTimeMapper = availibilityTimeMapper;
		this.gameMapper = gameMapper;
	}

	@Override
	public List<AvailibilityTimeTO> addAvailibilityTimeForUser(UserTO userTO,
			AvailibilityTimeTO timeTO) {
		
		UserEntity userEntity = userMapper.map(userTO);
		AvailibilityTimeEntity timeEntity = availibilityTimeMapper.map(timeTO);
		String eMail = userEntity.getEMail();
		
		userRepository.addAvailibilityTimeToList(eMail, timeEntity);
		
		return availibilityTimeMapper.map2TO(userRepository.getAvailibilityTimeList(eMail));
	}

	@Override
	public List<AvailibilityTimeTO> updateAvailibilityTime(UserTO userTO, AvailibilityTimeTO timeTO) {
		
		UserEntity userEntity = userMapper.map(userTO);
		AvailibilityTimeEntity timeEntity = availibilityTimeMapper.map(timeTO);
		String eMail = userEntity.getEMail();
		
		userRepository.updateAvailibilityTime(eMail, timeEntity);
		
		return availibilityTimeMapper.map2TO(userRepository.getAvailibilityTimeList(eMail));
	}

	@Override
	public List<UserTO> getUsersWithSimilarAvailibilityTime(AvailibilityTimeTO timeTO, GameTO gameTO) {
		
		AvailibilityTimeEntity timeEntity = availibilityTimeMapper.map(timeTO);
		GameEntity gameEntity = gameMapper.map(gameTO);
		
		List<UserEntity> usersListWithTheSameGame = userRepository.getUsersByGameType(gameEntity);
		List<UserEntity> usersListWithSimilarTime = userRepository.findUsersByAvailibilityTime(usersListWithTheSameGame, timeEntity);
		
		return userMapper.map2TO(usersListWithSimilarTime);
	}

}









