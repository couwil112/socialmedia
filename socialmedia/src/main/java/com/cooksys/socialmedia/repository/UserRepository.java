package com.cooksys.socialmedia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cooksys.socialmedia.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	User getByCredentials_Username(String username);
	
}
