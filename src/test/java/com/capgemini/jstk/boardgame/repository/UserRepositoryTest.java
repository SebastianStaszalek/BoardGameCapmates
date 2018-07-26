package com.capgemini.jstk.boardgame.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.capgemini.jstk.boardgame.domain.GameEntity;
import com.capgemini.jstk.boardgame.domain.UserEntity;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

	@Autowired
	UserRepository userRepository;
	
	//TODO: Jak uzywac @PreConstruct i dzialac w kazdym tescie na liscie niezaleznie??
	
	@Test
	public void shouldFindUserByEMail() {
		//given
				
		//when
		UserEntity user = userRepository.getUserByEMail("test@wp.pl");
		
		//then
		assertEquals("Ola", user.getFirstName());
		assertEquals("Obroszko", user.getLastName());
		assertEquals("RKS!", user.getMotto());
		assertEquals("tajne2", user.getPassword());
	}
	
	@Test
	public void shouldRemoveUserFromList() {
		//given
		
		//when
		userRepository.delete("mm@wp.pl");
		
		boolean exceptionThrown = false;
		try {
			userRepository.getUserByEMail("mm@wp.pl");
		} catch (RuntimeException e) {
			exceptionThrown = true;
		}
		// then 
		assertTrue(exceptionThrown);
	}
	
	@Test
	public void shouldAddNewUserToList() {
		//given
		
		//when
		UserEntity user = UserEntity.builder()
				.firstName("Maciej")
				.lastName("Nowak")
				.eMail("aa@gmail.com")
				.password("asd")
				.build();
		
		userRepository.createUser(user);
		
		//then
		assertEquals("Nowak", userRepository.getUserByEMail("aa@gmail.com").getLastName());
		assertNull(userRepository.getUserByEMail("aa@gmail.com").getMotto());
	}
	
	@Test
	public void shouldUpdateUserProfile() {
		//given
		UserEntity user = UserEntity.builder()
				.firstName("Jakub")
				.eMail("mail@onet.pl")
				.password("newPassword")
				.motto("You can everything")
				.build();
		
		//when
		userRepository.update(user);
		
		//then 
		assertEquals("newPassword", userRepository.getUserByEMail("mail@onet.pl").getPassword());
		assertNull(userRepository.getUserByEMail("mail@onet.pl").getLastName());
	}
	
	@Test 
	public void shouldAddGameToUserCollection() {
		//given 
		GameEntity game = GameEntity.builder()
				.name("WarGames")
				.description("Best game ever")
				.minimumPlayers(2)
				.maximumPlayers(4)
				.build();
		
		GameEntity game2 = GameEntity.builder()
				.name("Jungle Speed")
				.description("Be fast")
				.minimumPlayers(2)
				.maximumPlayers(6)
				.build();
		
		//when 
		userRepository.addGameToCollection("kuzniar@gmail.com", game);
		userRepository.addGameToCollection("kuzniar@gmail.com", game2);
		
		//then 
		int sizeOfCollection = userRepository.getGameCollection("kuzniar@gmail.com").size();
		assertEquals(2, sizeOfCollection);
	}
	
	@Test
	public void shouldDeleteGameFromUserCollection() {
		//given
		GameEntity game = GameEntity.builder()
				.name("Chess")
				.description("Classic game")
				.minimumPlayers(2)
				.maximumPlayers(2)
				.build();
		
		GameEntity game2 = GameEntity.builder()
				.name("Jungle Speed")
				.description("Be fast")
				.minimumPlayers(2)
				.maximumPlayers(6)
				.build();
		
		//when 
		userRepository.addGameToCollection("mm@wp.pl", game);
		userRepository.addGameToCollection("mm@wp.pl", game2);
		userRepository.removeGameFromCollection("mm@wp.pl", game);
		
		//then 
		int sizeOfCollection = userRepository.getGameCollection("mm@wp.pl").size();
		assertEquals(1, sizeOfCollection);
	}

}
