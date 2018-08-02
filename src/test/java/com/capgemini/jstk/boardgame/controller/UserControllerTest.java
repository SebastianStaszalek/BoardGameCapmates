package com.capgemini.jstk.boardgame.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.capgemini.jstk.boardgame.BoardgameApplication;
import com.capgemini.jstk.boardgame.domain.GameEntity;
import com.capgemini.jstk.boardgame.dto.UserSearchTO;
import com.capgemini.jstk.boardgame.dto.UserTO;
import com.capgemini.jstk.boardgame.service.UserProfileService;
import com.fasterxml.jackson.databind.ObjectMapper;

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
	public void shouldReturnUserByEmail() throws Exception {
		//given
		UserTO user = UserTO.builder().firstName("Seba").lastName("Nowy").eMail("yes@wp.pl").motto("motto").build();
		
		Mockito.when(userProfileService.getProfileInformation(Mockito.any())).thenReturn(user);
		
		// when
		ResultActions resultActions = mockMvc.perform(get("/users/{email}", "yes@wp.pl"));
		
		// then
		resultActions.andExpect(status().isFound())
			.andExpect(jsonPath("firstName").value("Seba"))
			.andReturn();
	}
	
	@Test
    public void shouldReturnUsers() throws Exception {
        //given
       List<UserTO> userProfiles = new ArrayList<>();
       
       userProfiles.add(UserTO.builder().firstName("Seba").lastName("Nowy").eMail("yes@wp.pl").motto("motto").build());
       userProfiles.add(UserTO.builder().firstName("Maciek").lastName("Walkowiak").eMail("abc@wp.pl").motto("YOLO!").build());
       
        Mockito.when(userProfileService.getAllUsers()).thenReturn(userProfiles);
        
        //when
        ResultActions resultActions = mockMvc.perform(get("/users/"));
        
        //then
        resultActions.andExpect(status().isFound()).andExpect(jsonPath("$[0]firstName")
                .value("Seba")).andExpect(jsonPath("$[1]lastName").value("Walkowiak"));

    }
	
	@Test
	public void shouldAddNewUser() throws Exception {
		//given
		UserTO userTO = UserTO.builder().firstName("Maciek").lastName("Walkowiak").eMail("abc@wp.pl").motto("YOLO!").build();
		
		Mockito.when(userProfileService.createUserProfile(Mockito.any(UserTO.class))).thenReturn(userTO);
		
		//when
		ObjectMapper mapper = new ObjectMapper();
		String newUser = mapper.writeValueAsString(userTO);
		
		ResultActions resultActions = mockMvc.perform(post("/users/").content(newUser).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
		
		//then
		resultActions.andExpect(status().isOk()).andExpect(jsonPath("motto").value("YOLO!")).andReturn();
	}
	
	@Test
	public void shouldUpdateUser() throws Exception {
		//given
		UserTO userTO = UserTO.builder().firstName("Joy").lastName("Spencer").eMail("ab@gmail.com").motto("YOLO!").build();
		
		Mockito.when(userProfileService.update(Mockito.any(UserTO.class))).thenReturn(userTO);
		
		//when
		ObjectMapper mapper = new ObjectMapper();
		String newUser = mapper.writeValueAsString(userTO);
		
		ResultActions resultActions = mockMvc.perform(put("/users/").content(newUser).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
		
		//then
		resultActions.andExpect(status().isOk()).andExpect(jsonPath("lastName").value("Spencer")).andReturn();
	}
	
	@Test
	public void shouldDeleteUser() throws Exception {
		//given
		UserTO userTO = UserTO.builder().firstName("Mark").lastName("Rainbow").eMail("mark@gmail.com").motto("Always riks!").build();
		
		Mockito.when(userProfileService.deleteUser(Mockito.anyString())).thenReturn(userTO);
		
		//when
		ResultActions resultActions = mockMvc.perform(delete("/users/{email}", "mark@gmail.com"));
		
		//then
		resultActions.andExpect(status().isOk()).andExpect(jsonPath("firstName").value("Mark")).andReturn();
	}
	
	//TODO:dlaczego ten test nie przechodzi!? w Postmanie wyszukuje po roznych kryteriach
	@Test
	public void shouldFindUsersByMultipleParams() throws Exception {
		//given
		List<UserTO> usersProfiles = new ArrayList<>();
		Set<GameEntity> gameCollection = new HashSet<>();
		
		gameCollection.add(GameEntity.builder().name("Chess").minimumPlayers(2).maximumPlayers(2).build());
		
		usersProfiles.add(UserTO.builder().firstName("Seba").lastName("Nowy").eMail("yes@wp.pl").motto("motto").gamesCollection(gameCollection).build());
		usersProfiles.add(UserTO.builder().firstName("Seba").lastName("Rainbow").eMail("mark@gmail.com").motto("yep").gamesCollection(gameCollection).build());
		usersProfiles.add(UserTO.builder().firstName("Seba").lastName("Spencer").eMail("ab@gmail.com").motto("boring!").gamesCollection(gameCollection).build());
		
		Mockito.when(userProfileService.findUserByMultipleParam(Mockito.any(UserSearchTO.class))).thenReturn(usersProfiles);
		
		//when
		UserSearchTO userSearchTO = UserSearchTO.builder().firstName("Seba").lastName(null).eMail(null).gameName("Chess").build();
		
		ObjectMapper mapper = new ObjectMapper();
		String searchParams = mapper.writeValueAsString(userSearchTO);
		
		ResultActions resultActions = mockMvc.perform(post("/users/search").content(searchParams).content(MediaType.APPLICATION_JSON_UTF8_VALUE));
		
		//then
		resultActions.andExpect(status().isFound())
		.andExpect(jsonPath("$[0]firstName").value("Seba"))
		.andExpect(jsonPath("$[1]firstName").value("Seba"))
		.andExpect(jsonPath("$[2]firstName").value("Seba")).andReturn();
	}

	 @Test
	    public void shouldReturnError404WhenNoPlayerWithWrongEMail() throws Exception {
		 
		 //given
		 Mockito.when(userProfileService.getProfileInformation(Mockito.anyString())).thenReturn(null);
		 
	     //when
	     ResultActions resultActions = mockMvc.perform(get("/users/{email}", "seba@gmail.com"));
	        
	     //then
	     resultActions
	        .andExpect(status().is4xxClientError());
	    }
	
	
}










