package com.capgemini.jstk.boardgame.service.implementation;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.jstk.boardgame.domain.GameEntity;
import com.capgemini.jstk.boardgame.domain.UserEntity;
import com.capgemini.jstk.boardgame.dto.GameTO;
import com.capgemini.jstk.boardgame.dto.UserTO;
import com.capgemini.jstk.boardgame.mapper.GameMapper;
import com.capgemini.jstk.boardgame.mapper.UserMapper;
import com.capgemini.jstk.boardgame.repository.GameRepository;
import com.capgemini.jstk.boardgame.repository.UserRepository;
import com.capgemini.jstk.boardgame.service.UserGamesCollectionService;

@Service
public class UserGamesCollectionServiceImp implements UserGamesCollectionService {

	private UserRepository userRepository;
	private UserMapper userMapper;
	
	private GameRepository gameRepository;
	private GameMapper gameMapper;
	
	@Autowired
	public UserGamesCollectionServiceImp(UserRepository userRepository, UserMapper userMapper,
			GameRepository gameRepository, GameMapper gameMapper) {
		this.userRepository = userRepository;
		this.userMapper = userMapper;
		this.gameRepository = gameRepository;
		this.gameMapper = gameMapper;
	}
	//TODO: czy nie ucinam gdzies pol z Usera jezeli one nie dodaja sie w maperze?
	@Override
	public Set<GameTO> getGamesCollection(UserTO userTO) {
		UserEntity userEntity = userMapper.map(userTO);
		Set<GameEntity> gamesCollection = userRepository.getGameCollection(userEntity.getEMail());
		return gameMapper.map2TO(gamesCollection);
	}

	@Override
	public void removeGameFromCollection(UserTO userTO, GameTO gameTO) {
		UserEntity userEntity = userMapper.map(userTO);
		GameEntity gameEntity = gameMapper.map(gameTO);
		
		userRepository.removeGameFromCollection(userEntity.getEMail(), gameEntity);
	}
	
	@Override
	public Set<GameTO> addGameToGeneralCollection(UserTO userTO, GameTO gameTO) {
		UserEntity userEntity = userMapper.map(userTO);
		GameEntity gameEntity = gameMapper.map(gameTO);
		
		gameRepository.add(gameEntity);
		userRepository.addGameToCollection(userEntity.getEMail(), gameEntity);
	
		return gameMapper.map2TO(userRepository.getGameCollection(userEntity.getEMail()));
	}
	
	@Override
	public Set<GameTO> addGameToUserCollection(UserTO userTO, GameTO gameTO) {
		UserEntity userEntity = userMapper.map(userTO);
		GameEntity gameEntity = gameMapper.map(gameTO);
		
		gameEntity = gameRepository.findByName(gameEntity.getName());
		userRepository.addGameToCollection(userEntity.getEMail(), gameEntity);
		Set<GameEntity> gamesCollection = userRepository.getGameCollection(userEntity.getEMail());
		
		return gameMapper.map2TO(gamesCollection);
	}
	
	
	
}
