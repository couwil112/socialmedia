package com.cooksys.socialmedia.dto;

import java.sql.Timestamp;

public class UserDto {

	private String username;
	private ProfileDto profileDto;
	private Timestamp joined;
	
	public UserDto() {
		
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public ProfileDto getProfile() {
		return profileDto;
	}

	public void setProfile(ProfileDto profileDto) {
		this.profileDto = profileDto;
	}

	public Timestamp getJoined() {
		return joined;
	}

	public void setJoined(Timestamp joined) {
		this.joined = joined;
	}	
}
