package com.capgemini.jstk.boardgame.service.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.jstk.boardgame.domain.UserEntity;
import com.capgemini.jstk.boardgame.domain.errors.UserNotFoundException;
import com.capgemini.jstk.boardgame.dto.UserSearchTO;
import com.capgemini.jstk.boardgame.dto.UserTO;
import com.capgemini.jstk.boardgame.mapper.UserMapper;
import com.capgemini.jstk.boardgame.repository.UserRepository;
import com.capgemini.jstk.boardgame.service.UserProfileService;

@Service
public class UserProfileServiceImp implements UserProfileService {

	private UserRepository userRepository;
	private UserMapper userMapper;

	@Autowired
	public UserProfileServiceImp(UserRepository userRepository, UserMapper userMapper) {
		this.userRepository = userRepository;
		this.userMapper = userMapper;
	}

	@Override
	public UserTO getProfileInformation(String eMail) {
		return userMapper.map(userRepository.getUserByEMail(eMail));
	}

	@Override
	public List<UserTO> getAllUsers() {
		return userMapper.map2TO(userRepository.getAllUsers());
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

	@Override
	public UserTO deleteUser(String eMail) {
		return userMapper.map(userRepository.delete(eMail));
	}

	@Override
	public List<UserTO> findUserByMultipleParam(UserSearchTO user) {

		String eMail = user.getEMail();
		String firstName = user.getFirstName();
		String lastName = user.getLastName();
		String gameName = user.getGameName();

		List<UserEntity> resultList = userRepository.getAllUsers();
		

		if (eMail.length() > 0) {
			try {
				resultList = resultList.stream()
						.filter(u -> u.getEMail().equals(eMail))
						.collect(Collectors.toList());
			} catch (UserNotFoundException ex) {
			}
		}

		if (firstName.length() > 0) {
			resultList = resultList.stream()
					.filter(u -> u.getFirstName().equalsIgnoreCase(firstName))
					.collect(Collectors.toList());
		}

		if (lastName.length() > 0) {
			resultList = resultList.stream()
					.filter(u -> u.getLastName().equalsIgnoreCase(lastName))
					.collect(Collectors.toList());
		}

		if (gameName.length() > 0) {
			resultList = resultList.stream()
			.filter(u -> u.getGamesCollection().stream()
			.anyMatch(g -> g.getName().equalsIgnoreCase(gameName)))
			.collect(Collectors.toList());
		}

		return userMapper.map2TO(resultList);
	}

}
