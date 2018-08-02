package com.capgemini.jstk.boardgame.repository.implementation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.capgemini.jstk.boardgame.domain.AvailibilityTimeEntity;
import com.capgemini.jstk.boardgame.domain.GameEntity;
import com.capgemini.jstk.boardgame.domain.GamesHistoryEntity;
import com.capgemini.jstk.boardgame.domain.UserEntity;
import com.capgemini.jstk.boardgame.domain.errors.AvailibilityTimeException;
import com.capgemini.jstk.boardgame.domain.errors.EmailDuplicateException;
import com.capgemini.jstk.boardgame.domain.errors.UserNotFoundException;
import com.capgemini.jstk.boardgame.repository.UserRepository;
import com.google.common.base.Preconditions;

@Repository
public class UserRepositoryImp implements UserRepository {

	private final static String EMAIL_IS_NULL = "The e-mail should not be empty";
	private final static String FIRST_NAME_IS_NULL = "The name should not be empty";
	private final static String LAST_NAME_IS_NULL = "The last name should not be empty";
	private final static String EMAIL_DUPLICATE = "The e-mail already exists, choose different one";
	private final static String USER_IS_NULL = "The fields should not be empty";
	private final static String USER_NOT_FOUND = "User not found";
	private final static String GAME_IS_EMPTY = "Game name should not be empty";
	private final static String AVAILIBILITY_TIME_IS_NULL = "Availibility time should not be empty";
	private final static String AVAILIBILITY_TIME_NOT_FOUND = "No match for this availibility time";

	List<UserEntity> usersList = new ArrayList<>();

	@PostConstruct
	public void initialize() {
		usersList.add(UserEntity.builder().eMail("abc@wp.pl").password("tajne1").firstName("Jakub")
				.lastName("Mackowiak").motto("YOLO!").build());
		usersList.add(UserEntity.builder().eMail("test@wp.pl").password("tajne2").firstName("Ola").lastName("Obroszko")
				.motto("RKS!").build());
		usersList.add(UserEntity.builder().eMail("mail@onet.pl").password("qwerty").firstName("Jakub")
				.lastName("Mackowiak").motto("Force be with you!").build());
		usersList.add(UserEntity.builder().eMail("mm@wp.pl").password("dupa").firstName("Magda").lastName("Malewska")
				.motto("Raz sie zyje!").build());
		usersList.add(UserEntity.builder().eMail("kuzniar@gmail.com").password("kuzniar").firstName("Marek")
				.lastName("Kuzniar").motto("Teraz albo wcale!").build());
	}


	public void clear() {
		this.usersList.clear();
	}
		
	@Override
	public UserEntity createUser(UserEntity newUser) {
		Preconditions.checkNotNull(newUser, USER_IS_NULL);

		if (usersList.stream().anyMatch(e -> e.getEMail().equals(newUser.getEMail()))) {
			throw new EmailDuplicateException(EMAIL_DUPLICATE);
		}
		this.usersList.add(newUser);
		return newUser;
	}

	@Override
	public UserEntity getUserByEMail(String eMail) {
		Preconditions.checkNotNull(eMail, EMAIL_IS_NULL);

		return usersList.stream().filter(e -> eMail.equals(e.getEMail())).findAny()
				.orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));
	}
	
	@Override
	public List<UserEntity> getUsersByFirstName(String firstName) {
		Preconditions.checkNotNull(firstName, FIRST_NAME_IS_NULL);
		
		return usersList.stream()
				.filter(f -> firstName.equalsIgnoreCase(f.getFirstName()))
				.collect(Collectors.toList());
	}


	@Override
	public List<UserEntity> getUsersByLastName(String lastName) {
		Preconditions.checkNotNull(lastName, LAST_NAME_IS_NULL);
		return usersList.stream()
				.filter(l -> lastName.equalsIgnoreCase(l.getLastName()))
				.collect(Collectors.toList());
	}
	

	@Override
	public List<UserEntity> getAllUsers() {
		return this.usersList;
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
	public UserEntity delete(String eMail) {
		Preconditions.checkNotNull(eMail, EMAIL_IS_NULL);
		
		UserEntity userToDelete = getUserByEMail(eMail);
		usersList.remove(getUserByEMail(eMail));
		
		return userToDelete;
	}

	@Override
	public void addGameToCollection(String eMail, GameEntity game) {
		Preconditions.checkNotNull(eMail, EMAIL_IS_NULL);
		Preconditions.checkNotNull(game, GAME_IS_EMPTY);

		UserEntity user = getUserByEMail(eMail);
		Set<GameEntity> gamesCollection = user.getGamesCollection();
		if(gamesCollection == null) {
			gamesCollection = new HashSet<>();
		}
		gamesCollection.add(game);
		user.setGamesCollection(gamesCollection);
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
		Preconditions.checkNotNull(game, GAME_IS_EMPTY);

		UserEntity user = getUserByEMail(eMail);
		Set<GameEntity> gamesCollection = user.getGamesCollection();
		gamesCollection.remove(game);
		user.setGamesCollection(gamesCollection);
	}

	@Override
	public void addRegistryToGameHistory(String eMail, GamesHistoryEntity gameHistory) {
		Preconditions.checkNotNull(eMail, EMAIL_IS_NULL);

		UserEntity user = getUserByEMail(eMail);
		user.getGamesHistory().add(gameHistory);
	}

	@Override
	public void addAvailibilityTimeToList(String eMail, AvailibilityTimeEntity availibilityTime) {
		Preconditions.checkNotNull(eMail, EMAIL_IS_NULL);
		Preconditions.checkNotNull(availibilityTime, AVAILIBILITY_TIME_IS_NULL);

		UserEntity user = getUserByEMail(eMail);
		List<AvailibilityTimeEntity> availibilityTimeList = user.getAvailibilityTime();
		if(availibilityTimeList == null) {
			availibilityTimeList = new ArrayList<>();
		}
		availibilityTimeList.add(availibilityTime);
		user.setAvailibilityTime(availibilityTimeList);
	}

	@Override
	public List<AvailibilityTimeEntity> getAvailibilityTimeList(String eMail) {
		Preconditions.checkNotNull(eMail, EMAIL_IS_NULL);

		UserEntity user = getUserByEMail(eMail);
		return user.getAvailibilityTime();
	}

	@Override
	public List<AvailibilityTimeEntity> updateAvailibilityTime(String eMail, AvailibilityTimeEntity availibilityTime) {
		Preconditions.checkNotNull(eMail, EMAIL_IS_NULL);
		Preconditions.checkNotNull(availibilityTime, AVAILIBILITY_TIME_IS_NULL);
		
		UserEntity user = getUserByEMail(eMail);
		
		List<AvailibilityTimeEntity> availibilityTimeList = user.getAvailibilityTime();
		AvailibilityTimeEntity timeToUpdate = getAvailibilityTimeById(eMail, availibilityTime.getId());
		
		availibilityTimeList.remove(timeToUpdate);
		availibilityTimeList.add(availibilityTime);
		
		return availibilityTimeList;
	}

	@Override
	public AvailibilityTimeEntity getAvailibilityTimeById(String eMail, Long iD) {
		
		List<AvailibilityTimeEntity> availibilityTimeList = getAvailibilityTimeList(eMail);
		
		return availibilityTimeList.stream().filter(a -> iD.equals(a.getId())).findAny()
				.orElseThrow(() -> new AvailibilityTimeException(AVAILIBILITY_TIME_NOT_FOUND));
	}


	@Override
	public List<UserEntity> getUsersByGameType(String gameName) {
		Preconditions.checkNotNull(gameName, GAME_IS_EMPTY);

		return usersList.stream()
				.filter(u -> u.getGamesCollection().stream()
				.anyMatch(g -> g.getName().equalsIgnoreCase(gameName)))
				.collect(Collectors.toList());
	}


	@Override
	public List<UserEntity> findUsersByAvailibilityTime(List<UserEntity> userList, AvailibilityTimeEntity time) {
		
		return userList.stream()
				.filter(a -> a.getAvailibilityTime().stream()
						.anyMatch(t -> 
								((t.getFrom().isAfter(time.getFrom()) && t.getFrom().isBefore(time.getTo()))
								|| (t.getTo().isAfter(time.getFrom()) && t.getTo().isBefore(time.getTo())))
								|| ((time.getTo().isAfter(t.getFrom()) && time.getTo().isBefore(t.getTo()))
								|| (time.getFrom().isAfter(t.getFrom()) && time.getFrom().isBefore(t.getTo())))))
				.collect(Collectors.toList());					
	}




	

}	
	
	
	
	
	