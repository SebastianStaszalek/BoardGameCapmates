package com.capgemini.jstk.boardgame.service;

import java.util.List;

import com.capgemini.jstk.boardgame.dto.UserSearchTO;
import com.capgemini.jstk.boardgame.dto.UserTO;

public interface UserProfileService {

	UserTO createUserProfile(UserTO user);
	
	UserTO getProfileInformation(String eMail);
	
	List<UserTO> getAllUsers();
	
	List<UserTO> findUserByMultipleParam(UserSearchTO user);
	
	UserTO update(UserTO user);
	
	void deleteUser(String eMail);
	
	}
