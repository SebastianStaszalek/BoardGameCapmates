package com.capgemini.jstk.boardgame;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.capgemini.jstk.boardgame.dto.UserTO;
import com.capgemini.jstk.boardgame.service.UserProfileService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BoardgameApplicationTests {
	
	@Autowired
	UserProfileService userProfileService;

	@Test
	public void shouldUpdateUserProfile() {
		//given
		UserTO userToUpdate = UserTO.builder()
				.eMail("test@wp.pl")
				.password("newPass")
				.firstName("Ola")
				.lastName("Obroszko")
				.build();
		//when
		userProfileService.update(userToUpdate);
	
		UserTO usertTOCheck = userProfileService.getProfileInformation("test@wp.pl");
		//then
		assertEquals("newPass", usertTOCheck.getPassword());
		assertEquals(null, usertTOCheck.getMotto());
	}

}
