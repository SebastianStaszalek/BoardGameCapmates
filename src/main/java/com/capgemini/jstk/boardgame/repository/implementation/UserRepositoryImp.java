package com.capgemini.jstk.boardgame.repository.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.util.Preconditions;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.stereotype.Repository;

import com.capgemini.jstk.boardgame.domain.GameEntity;
import com.capgemini.jstk.boardgame.domain.GamesHistoryEntity;
import com.capgemini.jstk.boardgame.domain.UserEntity;
import com.capgemini.jstk.boardgame.repository.UserRepository;

@Repository
public class UserRepositoryImp implements UserRepository {
	// TODO: moze zrob Enuma z Error messegami!?
	private final static String EMAIL_IS_NULL = "The e-mail should not be empty";
	private final static String EMAIL_DUPLICATE = "The e-mail already exists, choose different one";
	private final static String USER_IS_NULL = "The fields should not be empty";

	List<UserEntity> usersList = new ArrayList<>();

	@Override
	public UserEntity createUser(UserEntity newUser) {
		Preconditions.checkNotNull(newUser, USER_IS_NULL);
		
		if (usersList.stream().anyMatch(e -> e.getEMail().equals(newUser.getEMail()))) {
			throw new RuntimeException(EMAIL_DUPLICATE);
		}

		this.usersList.add(newUser);
		
		return newUser;
	}

	@Override
	public UserEntity getUserByEMail(String eMail) {
		Preconditions.checkNotNull(eMail, EMAIL_IS_NULL);

		return usersList.stream()
				.filter(e -> eMail.equals(e.getEMail()))
				.findAny()
				.orElse(null);
	}

	@Override
	public UserEntity update(UserEntity user) {
		Preconditions.checkNotNull(user, USER_IS_NULL);
		
		UserEntity userToUpdate = getUserByEMail(user.getEMail());
		usersList.remove(userToUpdate);
		usersList.add(user);
		
		return user;
	}

	@Override
	public void delete(String eMail) {
		Preconditions.checkNotNull(eMail, EMAIL_IS_NULL);

		usersList.remove(getUserByEMail(eMail));

	}

	@Override
	public void addGameToCollection(String eMail, GameEntity game) {
		Preconditions.checkNotNull(eMail, EMAIL_IS_NULL);

		UserEntity user = getUserByEMail(eMail);
		user.getGamesCollection().add(game);
	}

	@Override
	public void removeGameFromCollection(String eMail, GameEntity game) {
		Preconditions.checkNotNull(eMail, EMAIL_IS_NULL);
		
		UserEntity user = getUserByEMail(eMail);
		user.getGamesCollection().remove(Optional.ofNullable(game));
	}

	@Override
	public void addRegistryToGameHistory(String eMail, GamesHistoryEntity gameHistory) {
		Preconditions.checkNotNull(eMail, EMAIL_IS_NULL);
		
		UserEntity user = getUserByEMail(eMail);
		user.getGamesHistory().add(gameHistory);
	}

}
