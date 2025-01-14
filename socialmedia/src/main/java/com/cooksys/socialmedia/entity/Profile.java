package com.cooksys.socialmedia.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Profile {
	
	private String firstName;
	private String lastName;
	
	@Column(nullable = false)
	private String email;
	
	private String phone;
	
	public Profile() {
		
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
