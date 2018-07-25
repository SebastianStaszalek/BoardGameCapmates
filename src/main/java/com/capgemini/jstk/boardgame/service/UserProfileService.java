package com.capgemini.jstk.boardgame.service;

import com.capgemini.jstk.boardgame.dto.UserTO;

public interface UserProfileService {

	UserTO createUserProfile(UserTO user);
	
	UserTO getProfileInformation(String eMail);
	
	UserTO update(UserTO user);
	
	}