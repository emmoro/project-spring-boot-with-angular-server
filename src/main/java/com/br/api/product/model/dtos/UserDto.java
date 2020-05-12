package com.br.api.product.model.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

public class UserDto {

	private String name;
	
	private String username;
	
	private String password;

	private String email;
	
	private String lastName;

	@NotEmpty(message = "Name cannot be empty.")
	@Length(min = 3, max = 200, message = "Name must contain between 3 and 200 characters.")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@NotEmpty(message = "UserName cannot be empty.")
	@Length(min = 3, max = 200, message = "UserName must contain between 3 and 200 characters.")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@NotEmpty(message = "Email cannot be empty.")
	@Length(min = 5, max = 200, message = "Email must contain between 5 and 200 characters.")
	@Email(message="Email invalid.")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@NotEmpty(message = "Last Name cannot be empty.")
	@Length(min = 3, max = 200, message = "Last Name must contain between 3 and 200 characters.")
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "UserDto [name=" + name + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", lastName=" + lastName + "]";
	}
	
}
