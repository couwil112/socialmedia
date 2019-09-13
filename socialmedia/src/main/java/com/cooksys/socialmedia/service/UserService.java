package com.cooksys.socialmedia.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cooksys.socialmedia.dto.CreateUserDto;
import com.cooksys.socialmedia.dto.CredentialsDto;
import com.cooksys.socialmedia.dto.ProfileDto;
import com.cooksys.socialmedia.dto.UserDto;
import com.cooksys.socialmedia.entity.Profile;
import com.cooksys.socialmedia.entity.User;
import com.cooksys.socialmedia.mapper.UserMapper;
import com.cooksys.socialmedia.repository.UserRepository;

@Service
public class UserService {

	private final ResponseEntity<UserDto> CONFLICT = new ResponseEntity<>(null, HttpStatus.CONFLICT);
	
	private UserMapper userMapper;
	private UserRepository userRepository;	
	
	public UserService(UserMapper userMapper, UserRepository userRepository) {
		this.userMapper = userMapper;
		this.userRepository = userRepository;
	}
	
	public ResponseEntity<UserDto> createUser(CreateUserDto createUserDto) {
		String username = createUserDto.getCredentials().getUsername();
		String password = createUserDto.getCredentials().getPassword();
		String email = createUserDto.getProfile().getEmail();
		
		if (requiredInfoMissing(username, password, email)) {
			return CONFLICT;
		}		
		if (validateUsernameAvailable(username)) {
			return new ResponseEntity<>(userMapper.entityToDto(userRepository.saveAndFlush(
					userMapper.createUserDtoToEntity(createUserDto))), HttpStatus.CREATED);
		} else {
			User user = getUserByCredentials(createUserDto.getCredentials());
			if (user != null && user.getIsDeactivated()) { 
				user.setDeactivated(false);
				return new ResponseEntity<>(userMapper.entityToDto(
						userRepository.save(user)), HttpStatus.OK);
			}
		}
		return CONFLICT;
	}

	public ResponseEntity<List<UserDto>> getAll() {
		return new ResponseEntity<>(userMapper.entitiesToDtos(userRepository.findAll()), HttpStatus.OK);
	}

	public ResponseEntity<UserDto> getByUsername(String username) {
		User userToDelete = userRepository.getByCredentials_Username(username);
		
		if (userToDelete != null) {
			UserDto userDto = userMapper.entityToDto(userToDelete);
			return new ResponseEntity<>(userDto, HttpStatus.OK);
		}		
		return CONFLICT;
	}

	public boolean validateUsernameExists(String username) {
		return !getByUsername(username).equals(new ResponseEntity<>(HttpStatus.CONFLICT));
	}

	public boolean validateUsernameAvailable(String username) {		
		return !validateUsernameExists(username);
	}
	
	public ResponseEntity<UserDto> updateUserProfile(CreateUserDto createUserDto) {
		CredentialsDto credentialsDto = createUserDto.getCredentials();
		ProfileDto profileDto = createUserDto.getProfile();
		if (requiredInfoMissing(credentialsDto.getUsername(), credentialsDto.getPassword(), 
			profileDto.getEmail())) {
			return CONFLICT;
		}
		User userToUpdate = getUserByCredentials(credentialsDto);
		if (userToUpdate == null) {
			return CONFLICT;
		}
		updateProfile(userToUpdate.getProfile(), profileDto);
		return new ResponseEntity<>(userMapper.entityToDto(
				userRepository.save(userToUpdate)), HttpStatus.OK);
	}

	public ResponseEntity<UserDto> deleteUser(CredentialsDto credentialsDto) {
		if (requiredInfoMissing(credentialsDto.getUsername(), credentialsDto.getPassword())) {
			return CONFLICT;
		}		
		User userToDelete = getUserByCredentials(credentialsDto);
		if (userToDelete != null) {
			userToDelete.setDeactivated(true);
			return new ResponseEntity<>(userMapper.entityToDto(
					userRepository.save(userToDelete)), HttpStatus.NO_CONTENT);
		
		}
		return CONFLICT;
	}
	
	private boolean requiredInfoMissing(String ...args) {
		for (String arg : args) {
			if (arg == null) {
				return true;
			}
		}
		return false;
	}
	
	private User getUserByCredentials(CredentialsDto credentialsDto) {
		if(validateUsernameExists(credentialsDto.getUsername())) {
			User user = userRepository.getByCredentials_Username(credentialsDto.getUsername());
			if(credentialsDto.getPassword().equals(user.getCredentials().getPassword())) {
				return user;
			}
		}
		return null;
	}
	
	private void updateProfile(Profile userProfile, ProfileDto updatedProfile) {
		if (updatedProfile.getFirstName() != null && 
				!userProfile.getFirstName().equals(updatedProfile.getFirstName())) {
			userProfile.setFirstName(updatedProfile.getFirstName());
		}
		if (updatedProfile.getLastName() != null &&
				!userProfile.getLastName().equals(updatedProfile.getLastName())) {
			userProfile.setLastName(updatedProfile.getLastName());
		}
		if (updatedProfile.getEmail() != null &&
				!userProfile.getEmail().equals(updatedProfile.getEmail())) {
			userProfile.setEmail(updatedProfile.getEmail());
		}
		if (updatedProfile.getPhone() != null &&
				!userProfile.getPhone().equals(updatedProfile.getPhone())) {
			userProfile.setPhone(updatedProfile.getPhone());
		}
	}
}
