package com.capgemini.jstk.boardgame.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.jstk.boardgame.dto.UserSearchTO;
import com.capgemini.jstk.boardgame.dto.UserTO;
import com.capgemini.jstk.boardgame.service.UserProfileService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	

	private UserProfileService userProfileService;
	

	@Autowired
	public UserController(UserProfileService userProfileService) {
		this.userProfileService = userProfileService;
	}
	
	
	@GetMapping(value = "/get")
	public ResponseEntity<List<UserTO>> getAllUsers() {
		List<UserTO> usersList = userProfileService.getAllUsers();
		return new ResponseEntity<>(usersList, HttpStatus.FOUND);
	}
	
	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<UserTO> addNewUser(@RequestBody UserTO userTO) {
		UserTO newUser = userProfileService.createUserProfile(userTO);
		return new ResponseEntity<>(newUser, HttpStatus.OK);
	}
	
	@GetMapping(value = "/find/{email}")
	public ResponseEntity<UserTO> getUserByEMail(@PathVariable("email") String eMail) {
		UserTO userToFind = userProfileService.getProfileInformation(eMail);
		return new ResponseEntity<>(userToFind, HttpStatus.FOUND);
	}
	
	@PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<UserTO> updateUserProfile(@RequestBody UserTO userTO) {
		UserTO userToUpdate = userProfileService.update(userTO);
		return new ResponseEntity<>(userToUpdate, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{email}")
	public ResponseEntity<UserTO> deleteUser(@PathVariable("email") String eMail) {
		UserTO userToDelete = userProfileService.deleteUser(eMail);
		return new ResponseEntity<>(userToDelete, HttpStatus.OK);
	}
	
	@PostMapping(value = "/search", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<UserTO>> findUsers(@RequestBody UserSearchTO user) {
		List<UserTO> usersList = userProfileService.findUserByMultipleParam(user);
		return new ResponseEntity<>(usersList, HttpStatus.FOUND);
	}
	
	
	
}






