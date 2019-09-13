package com.cooksys.socialmedia.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.socialmedia.dto.CreateUserDto;
import com.cooksys.socialmedia.dto.CredentialsDto;
import com.cooksys.socialmedia.dto.ProfileDto;
import com.cooksys.socialmedia.dto.UserDto;
import com.cooksys.socialmedia.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	private UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}
    
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody CreateUserDto createUserDto) {
        return userService.createUser(createUserDto);
    }
    
	@GetMapping("/@{username}")
	public ResponseEntity<UserDto> getByUsername(@PathVariable String username) {
		return userService.getByUsername(username);
	}
	
    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers() {
        return userService.getAll();
    }
    
    @GetMapping("/validate/username/exists/@{username}")
    public boolean validateUsernameExists(@PathVariable String username) {
    	return userService.validateUsernameExists(username);
    }
    
    @GetMapping("/validate/username/available/@{username}")
    public boolean validateUsernameAvaliable(@PathVariable String username) {
    	return userService.validateUsernameAvailable(username);
    }
    
    @PatchMapping("/@{username}")
    public ResponseEntity<UserDto> updateUserProfile(@RequestBody CreateUserDto createUserDto) {
    	return userService.updateUserProfile(createUserDto);
    }
    
    @DeleteMapping("/@{username}")
    public ResponseEntity<UserDto> deleteUser(@RequestBody CredentialsDto credentialsDto) {
    	return userService.deleteUser(credentialsDto);
    }
}
