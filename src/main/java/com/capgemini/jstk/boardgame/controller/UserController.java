package com.capgemini.jstk.boardgame.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
	
	@PostMapping(value = "/add")
	public UserTO addNewUser(@RequestBody UserTO userTO) {
		return userProfileService.createUserProfile(userTO);
	}
	
	@GetMapping(value = "/find/{email}")
	public UserTO findtUserByEMail(@PathVariable("email") String eMail) {
		return userProfileService.getProfileInformation(eMail);
	}
	
	@PutMapping(value = "/update")
	public UserTO updateUserProfile(@RequestBody UserTO userTO) {
		return userProfileService.update(userTO);
	}
	
	@DeleteMapping(value = "/delete/{email}")
	public void deleteUser(@PathVariable("email") String eMail) {
		userProfileService.deleteUser(eMail);
	}
	
}
