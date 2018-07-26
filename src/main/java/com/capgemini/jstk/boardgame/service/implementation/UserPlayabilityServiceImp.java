package com.capgemini.jstk.boardgame.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.jstk.boardgame.domain.AvailibilityTimeEntity;
import com.capgemini.jstk.boardgame.domain.UserEntity;
import com.capgemini.jstk.boardgame.dto.AvailibilityTimeTO;
import com.capgemini.jstk.boardgame.dto.UserTO;
import com.capgemini.jstk.boardgame.mapper.AvailibilityTimeMapper;
import com.capgemini.jstk.boardgame.mapper.UserMapper;
import com.capgemini.jstk.boardgame.repository.AvailibilityTimeRepository;
import com.capgemini.jstk.boardgame.repository.UserRepository;
import com.capgemini.jstk.boardgame.service.UserPlayabilityService;

@Service
public class UserPlayabilityServiceImp implements UserPlayabilityService {

	private UserRepository userRepository;
	private UserMapper userMapper;

	private AvailibilityTimeRepository availibilityTimeRepository;
	private AvailibilityTimeMapper availibilityTimeMapper;
	
	@Autowired
	public UserPlayabilityServiceImp(UserRepository userRepository, UserMapper userMapper,
			AvailibilityTimeRepository availibilityTimeRepository, AvailibilityTimeMapper availibilityTimeMapper) {
		this.userRepository = userRepository;
		this.userMapper = userMapper;
		this.availibilityTimeRepository = availibilityTimeRepository;
		this.availibilityTimeMapper = availibilityTimeMapper;
	}

	@Override
	public List<AvailibilityTimeTO> addAvailibilityTimeForUser(UserTO userTO,
			AvailibilityTimeTO availibilityTimeTO) {
		
		UserEntity userEntity = userMapper.map(userTO);
		AvailibilityTimeEntity availibilityTimeEntity = availibilityTimeMapper.map(availibilityTimeTO);
		String eMail = userEntity.getEMail();
		
		userRepository.addAvailibilityTimeToList(eMail, availibilityTimeEntity);
		
		return availibilityTimeMapper.map2TO(userRepository.getAvailibilityTimeList(eMail));
	}

	@Override
	public List<AvailibilityTimeTO> updateAvailibilityTime(UserTO userTO, AvailibilityTimeTO availibilityTimeTO) {
		
		UserEntity userEntity = userMapper.map(userTO);
		AvailibilityTimeEntity availibilityTimeEntity = availibilityTimeMapper.map(availibilityTimeTO);
		String eMail = userEntity.getEMail();
		
		userRepository.updateAvailibilityTime(eMail, availibilityTimeEntity);
		
		return availibilityTimeMapper.map2TO(userRepository.getAvailibilityTimeList(eMail));
	}

}









