package com.capgemini.jstk.boardgame.service;

import java.util.List;

import com.capgemini.jstk.boardgame.dto.UserTO;

public interface UserProfileService {

	UserTO createUserProfile(UserTO user);
	
	UserTO getProfileInformation(String eMail);
	
	List<UserTO> findUserByMultipleParam(UserTO user, String gameName);
	
	UserTO update(UserTO user);
	
	void deleteUser(String eMail);
	
	}
