package com.capgemini.jstk.boardgame.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.capgemini.jstk.boardgame.BoardgameApplication;
import com.capgemini.jstk.boardgame.dto.UserTO;
import com.capgemini.jstk.boardgame.service.UserProfileService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BoardgameApplication.class)
@WebAppConfiguration
public class UserControllerTest {
	

	private MockMvc mockMvc;
	
	@Autowired
    private WebApplicationContext webApplicationContext;
	
	@Mock
	private UserProfileService userProfileService;
	
	@Autowired
	private UserController userController;
	
	@Before
    public void setUp() {
        MockitoAnnotations.initMocks(userProfileService);
        Mockito.reset(userProfileService);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        ReflectionTestUtils.setField(userController, "userProfileService", userProfileService);
        
    }
	
	@Test
	public void shouldReturnPlayer() throws Exception {
		
		//given
		UserTO user = UserTO.builder().firstName("Seba").lastName("Nowy").eMail("yes@wp.pl").motto("motto").build();
		
		Mockito.when(userProfileService.getProfileInformation(Mockito.any())).thenReturn(user);
		
		// when
		ResultActions resultActions = mockMvc.perform(get("/users/find/{email}", "yes@wp.pl"));
		
		// then
		resultActions.andExpect(status().isFound())
			.andExpect(jsonPath("firstName").value("Seba"))
			.andReturn();
	}

	
	
}
