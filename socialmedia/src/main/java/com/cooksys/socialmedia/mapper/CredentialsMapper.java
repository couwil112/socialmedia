package com.cooksys.socialmedia.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.cooksys.socialmedia.dto.CredentialsDto;
import com.cooksys.socialmedia.entity.Credentials;

@Mapper(componentModel = "spring")
public interface CredentialsMapper {

	@Mapping(target = "username", source = "username")
	@Mapping(target = "password", source = "password")
	Credentials credentialsDtoToCredentials(CredentialsDto credentialsDto);
	
	@Mapping(target = "username", source = "username")
	@Mapping(target = "password", source = "password")
	CredentialsDto credentialsToCredentialsDto(Credentials credentials);
}
