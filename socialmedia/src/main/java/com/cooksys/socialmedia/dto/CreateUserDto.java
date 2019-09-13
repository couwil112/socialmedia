package com.cooksys.socialmedia.dto;

public class CreateUserDto {

	private CredentialsDto credentialsDto;
	private ProfileDto profileDto;
	
	public CreateUserDto() {		
	}
		
	public CreateUserDto(CredentialsDto credentialsDto, ProfileDto profileDto) {
		this.credentialsDto = credentialsDto;
		this.profileDto = profileDto;
	}

	public CredentialsDto getCredentials() {
		return credentialsDto;
	}

	public void setCredentials(CredentialsDto credentialsDto) {
		this.credentialsDto = credentialsDto;
	}

	public ProfileDto getProfile() {
		return profileDto;
	}

	public void setProfile(ProfileDto profileDto) {
		this.profileDto = profileDto;
	}
}
