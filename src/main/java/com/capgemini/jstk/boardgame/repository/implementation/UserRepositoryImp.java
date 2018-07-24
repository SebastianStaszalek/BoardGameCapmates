package com.capgemini.jstk.boardgame.repository.implementation;

import java.util.ArrayList;
import java.util.List;

import com.capgemini.jstk.boardgame.domain.UserEntity;
import com.capgemini.jstk.boardgame.repository.UserRepository;

public class UserRepositoryImp implements UserRepository {

	List<UserEntity> usersList = new ArrayList<>();
	
	@Override
	public void createUser(String eMail, String name, String surname, String password, String motto) {
		UserEntity newUser = UserEntity.builder()
			.eMail(eMail)
			.password(password)
			.firstName(name)
			.lastName(surname)
			.motto(motto)
			.build();
		
		this.usersList.add(newUser);
	}

	@Override
	public UserEntity getUserByEMail(String eMail) {
		return null;
	}

	@Override
	public void update(String eMail, String name, String surname, String password, String motto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String eMail) {
		// TODO Auto-generated method stub
		
	}

}
