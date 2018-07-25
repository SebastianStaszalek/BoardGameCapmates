package com.capgemini.jstk.boardgame.repository;

import com.capgemini.jstk.boardgame.domain.GameEntity;
import com.capgemini.jstk.boardgame.domain.GamesHistoryEntity;
import com.capgemini.jstk.boardgame.domain.UserEntity;

public interface UserRepository {
	
	void createUser(String eMail, String name, String surname, String password, String motto);
	
	UserEntity getUserByEMail(String eMail);
	
	void update(String eMail, String name, String surname, String password, String motto);
	
	void delete(String eMail);
	
	void addGameToCollection(String eMail, GameEntity game);
	
	void removeGameFromCollection(String eMail, GameEntity game);
	
	void addRegistryToGameHistory(String eMail, GamesHistoryEntity gameHistory);
	
	
	
	//TODO: czy metoda dodajaca AvailibilityTime do listy konkretnego uzytkownika powinna byc tutaj czy w Service??!!
	//TODO: to pytanie tyczy sie tez innych metod... np.: STATYSTKI, dodawanie gry do kolekcji z listy dostepnych gier,
}
