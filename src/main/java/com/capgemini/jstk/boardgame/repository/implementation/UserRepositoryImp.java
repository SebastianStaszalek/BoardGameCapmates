package com.capgemini.jstk.boardgame.repository.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.assertj.core.util.Preconditions;
import org.springframework.stereotype.Repository;

import com.capgemini.jstk.boardgame.domain.GameEntity;
import com.capgemini.jstk.boardgame.domain.GamesHistoryEntity;
import com.capgemini.jstk.boardgame.domain.UserEntity;
import com.capgemini.jstk.boardgame.repository.UserRepository;

@Repository
public class UserRepositoryImp implements UserRepository {
	
	private final static String EMAIL_IS_NULL = "The e-mail should not be empty";
	private final static String EMAIL_DUPLICATE = "The e-mail already exists, choose different one";
	private final static String USER_IS_NULL = "The fields should not be empty";
	private final static String USER_NOT_FOUND = "User not found";

	List<UserEntity> usersList = new ArrayList<>();
	
	@PostConstruct
	public void initialize() {
		usersList.add(UserEntity.builder().eMail("abc@wp.pl").password("tajne1").firstName("Jakub").lastName("Mackowiak").motto("YOLO!").build());
		usersList.add(UserEntity.builder().eMail("test@wp.pl").password("tajne2").firstName("Ola").lastName("Olobroszko").motto("RKS!").build());
		usersList.add(UserEntity.builder().eMail("mail@onet.pl").password("qwerty").firstName("Jakub").lastName("Mackowiak").motto("Force be with you!").build());
		usersList.add(UserEntity.builder().eMail("mm@wp.pl").password("dupa").firstName("Magda").lastName("Malewska").motto("Raz sie zyje!").build());
		usersList.add(UserEntity.builder().eMail("kuzniar@gmail.com").password("kuzniar").firstName("Marek").lastName("Kuzniar").motto("Teraz albo wcale!").build());
	}

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
				.orElseThrow(() -> new RuntimeException(USER_NOT_FOUND));
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
	public Set<GameEntity> getGameCollection(String eMail) {
		Preconditions.checkNotNull(eMail, EMAIL_IS_NULL);
		
		UserEntity user = getUserByEMail(eMail);
		return user.getGamesCollection();
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
