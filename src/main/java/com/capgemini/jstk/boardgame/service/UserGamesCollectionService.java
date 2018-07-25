package com.capgemini.jstk.boardgame.service;

import java.util.Set;

import com.capgemini.jstk.boardgame.dto.GameTO;
import com.capgemini.jstk.boardgame.dto.UserTO;

public interface UserGamesCollectionService {
	
	Set<GameTO> getGamesCollection(UserTO user);
	
	void removeGameFromCollection(UserTO userTO, GameTO gameTO);
	
	GameTO addGameToGeneralCollection(UserTO userTO, GameTO gameTO);
	
	Set<GameTO> addGameToUserCollection(UserTO userTO, GameTO gameTO);

}
