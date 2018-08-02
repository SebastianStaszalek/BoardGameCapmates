package com.capgemini.jstk.boardgame.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.capgemini.jstk.boardgame.domain.UserEntity;
import com.capgemini.jstk.boardgame.dto.UserTO;
import com.capgemini.jstk.boardgame.mapper.UserMapper;
import com.capgemini.jstk.boardgame.repository.UserRepository;
import com.capgemini.jstk.boardgame.service.implementation.UserProfileServiceImp;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserProfileServiceTest {
	
	@Spy
	UserMapper userMapper;
	
	@Mock
	UserRepository userRepository;
	
	@InjectMocks
	UserProfileServiceImp userProfileService;
	
	@Test 
	public void shouldReturnUserProfileInformation() {
		//given
		UserEntity user = UserEntity.builder()
				.eMail("jadzia@interia.pl")
				.firstName("Jadzia")
				.lastName("Dobrowolska")
				.password("jadziaxxx")
				.build();
		
		Mockito.when(userRepository.getUserByEMail(Mockito.anyString())).thenReturn(user);
		
		//when
		userProfileService.getProfileInformation("jadzia@interia.pl");
		//then
		Mockito.verify(userRepository).getUserByEMail(Mockito.anyString());
	}
	
	@Test
	public void shouldUpdateUserProfile() {
		//given
		UserEntity user = UserEntity.builder()
				.eMail("jadzia@interia.pl")
				.firstName("Jadzia")
				.lastName("Dobrowolska")
				.password("jadziaxxx")
				.build();
		
		UserTO userTO = UserTO.builder()
				.eMail("jadzia@interia.pl")
				.firstName("Jadzia")
				.lastName("Dobrowolska")
				.password("jadziaxxx")
				.build();
		
		
		Mockito.when(userRepository.update(Mockito.any(UserEntity.class))).thenReturn(user);
		//when
		userProfileService.update(userTO);
		//then
		Mockito.verify(userRepository).update(Mockito.any(UserEntity.class));
	}
	
	@Test
	public void shouldCreateUserProfile() {
		//given
		UserEntity user = UserEntity.builder()
				.eMail("madzix@wp.pl")
				.firstName("Madzia")
				.lastName("Janik")
				.password("mmm")
				.build();
		
		UserTO userTO = UserTO.builder()
				.eMail("madzix@wp.pl")
				.firstName("Madzia")
				.lastName("Janik")
				.password("mmm")
				.build();
		
		Mockito.when(userRepository.createUser(Mockito.any(UserEntity.class))).thenReturn(user);
		//when
		userProfileService.createUserProfile(userTO);
		//then
		Mockito.verify(userRepository).createUser(Mockito.any(UserEntity.class));
	}
	
	
}

	
