package com.capgemini.jstk.boardgame.service;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.capgemini.jstk.boardgame.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserProfileServiceTest {
	
	@Mock
	UserRepository userRepository;
	
	@InjectMocks
	UserProfileService userProfileService;
	
	
}

	
