package com.capgemini.jstk.boardgame.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.capgemini.jstk.boardgame.domain.GameEntity;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GameRepositoryTest {
	
	@Autowired
	GameRepository gameRepository;
	
	@Test
	public void shouldAddGameToGeneralList() {
		//given
		GameEntity game = GameEntity.builder()
				.name("Bingo")
				.description("Old cool game")
				.minimumPlayers(4)
				.maximumPlayers(12)
				.build();
		
		//when
		gameRepository.add(game);
		
		//then
		assertEquals("Old cool game", gameRepository.findByName("Bingo").getDescription());
	}
	
	@Test
	public void shouldFindGameByName() {
		//given
		
		//when
		GameEntity gameEntity = gameRepository.findByName("Duplo");
		
		//then
		assertEquals(2, gameEntity.getMinimumPlayers());
		assertEquals(5, gameEntity.getMaximumPlayers());
		assertEquals(null, gameEntity.getDescription());
	}
	
	@Test
	public void shouldReturnExceptionWhenGameNotFound() {
		//given
		
		//when
		boolean exceptionThrown = false;
		try {
			gameRepository.findByName("Domino");
		} catch (RuntimeException e) {
			exceptionThrown = true;
		}
		// then 
		assertTrue(exceptionThrown);
	}
	
	@Test
	public void shouldReturnExceptionWhenTryToAddGameWithTheSameName() {
		//given
		GameEntity game = GameEntity.builder()
				.name("Chess")
				.minimumPlayers(3)
				.maximumPlayers(3)
				.build();
		
		//when
		boolean exceptionThrown = false;
		try {
			gameRepository.add(game);
		} catch (RuntimeException e) {
			exceptionThrown = true;
		}
		// then 
		assertTrue(exceptionThrown);
	}
	
}
