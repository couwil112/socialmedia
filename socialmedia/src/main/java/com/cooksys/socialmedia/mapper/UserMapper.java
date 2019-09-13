package com.cooksys.socialmedia.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.cooksys.socialmedia.dto.CreateUserDto;
import com.cooksys.socialmedia.dto.UserDto;
import com.cooksys.socialmedia.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

	User createUserDtoToEntity(CreateUserDto createUserDto);

	@Mapping(target = "username", source = "credentials.username")
	UserDto entityToDto(User user);
	
	List<UserDto> entitiesToDtos(List<User> users);
}
