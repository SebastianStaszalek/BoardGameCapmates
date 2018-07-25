package com.capgemini.jstk.boardgame.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.jstk.boardgame.domain.UserEntity;
import com.capgemini.jstk.boardgame.dto.UserTO;
import com.capgemini.jstk.boardgame.mapper.UserMapper;
import com.capgemini.jstk.boardgame.repository.UserRepository;
import com.capgemini.jstk.boardgame.service.UserProfileService;

@Service
public class UserProfileServiceImp implements UserProfileService {
	
	private UserRepository userRepository;
	private UserMapper userMapper;
	
	@Autowired
	public UserProfileServiceImp(UserRepository userRepository,	UserMapper userMapper) {
		this.userRepository = userRepository;
		this.userMapper = userMapper;
	}

	@Override
	public UserTO getProfileInformation(String eMail) {
		return userMapper.map(userRepository.getUserByEMail(eMail));
	}

	@Override
	public UserTO update(UserTO user) {
		UserEntity entity = userMapper.map(user);
		entity = userRepository.update(entity);
		return userMapper.map(entity);
	}

	@Override
	public UserTO createUserProfile(UserTO user) {
		UserEntity entity = userMapper.map(user);
		entity = userRepository.createUser(entity);
		return userMapper.map(entity);
	}

}
