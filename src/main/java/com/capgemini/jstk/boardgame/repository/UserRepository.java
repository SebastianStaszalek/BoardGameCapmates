package com.capgemini.jstk.boardgame.repository;

import com.capgemini.jstk.boardgame.domain.UserEntity;

public interface UserRepository {
	
	void createUser(String eMail, String name, String surname, String password, String motto);
	UserEntity getUserByEMail(String eMail);
	void update(String eMail, String name, String surname, String password, String motto);
	void delete(String eMail);
}
