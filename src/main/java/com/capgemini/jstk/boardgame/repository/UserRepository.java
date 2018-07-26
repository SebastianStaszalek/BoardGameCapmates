package com.capgemini.jstk.boardgame.repository;

import java.util.List;
import java.util.Set;

import com.capgemini.jstk.boardgame.domain.AvailibilityTimeEntity;
import com.capgemini.jstk.boardgame.domain.GameEntity;
import com.capgemini.jstk.boardgame.domain.GamesHistoryEntity;
import com.capgemini.jstk.boardgame.domain.UserEntity;

public interface UserRepository {
	
	UserEntity createUser(UserEntity user);
	
	UserEntity getUserByEMail(String eMail);
	
	UserEntity update(UserEntity user);
	
	void delete(String eMail);
	
	void addGameToCollection(String eMail, GameEntity game);
	
	Set<GameEntity> getGameCollection(String eMail);
	
	void addAvailibilityTimeToList(String eMail, AvailibilityTimeEntity availibilityTime);
	
	List<AvailibilityTimeEntity> getAvailibilityTimeList(String eMail);
	
	List<AvailibilityTimeEntity> updateAvailibilityTime(String eMail, AvailibilityTimeEntity availibilityTime);
	
	AvailibilityTimeEntity getAvailibilityTimeById(String eMail, Long iD);
	
	void removeGameFromCollection(String eMail, GameEntity game);
	
	void addRegistryToGameHistory(String eMail, GamesHistoryEntity gameHistory);
	
	
	
	
}
