package com.capgemini.jstk.boardgame.repository.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.util.Preconditions;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.stereotype.Repository;

import com.capgemini.jstk.boardgame.domain.GameEntity;
import com.capgemini.jstk.boardgame.domain.UserEntity;
import com.capgemini.jstk.boardgame.repository.UserRepository;

@Repository
public class UserRepositoryImp implements UserRepository {
	// TODO: moze zrob Enuma z Error messegami!?
	private final static String EMAIL_IS_NULL = "The e-mail should not be empty";
	private final static String EMAIL_DUPLICATE = "The e-mail already exists, choose different one";

	List<UserEntity> usersList = new ArrayList<>();

	@Override
	public void createUser(String eMail, String name, String surname, String password, String motto) {
		Preconditions.checkNotNull(eMail, EMAIL_IS_NULL);

		if (usersList.stream().anyMatch(e -> e.getEMail().equals(eMail))) {
			throw new RuntimeException(EMAIL_DUPLICATE);
		}

		UserEntity newUser = UserEntity.builder().eMail(eMail).password(password).firstName(name).lastName(surname)
				.motto(motto).build();

		this.usersList.add(newUser);
	}

	@Override
	public UserEntity getUserByEMail(String eMail) {
		Preconditions.checkNotNull(eMail, EMAIL_IS_NULL);

		return usersList.stream()
				.filter(e -> eMail.equals(e.getEMail()))
				.findAny()
				.orElse(null);
	}

	// TODO: walidacja hasla jak bedzie czas?
	@Override
	public void update(String eMail, String name, String surname, String password, String motto) {
		Preconditions.checkNotNull(eMail, EMAIL_IS_NULL);

		UserEntity userToUpdate = getUserByEMail(eMail);
		userToUpdate.setFirstName(name);
		userToUpdate.setLastName(surname);
		userToUpdate.setPassword(password);
		userToUpdate.setPassword(motto);

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

}
