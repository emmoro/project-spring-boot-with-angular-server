package com.br.api.product.service;

import java.security.NoSuchAlgorithmException;

import org.springframework.validation.BindingResult;

import com.br.api.product.entities.UserTO;
import com.br.api.product.model.dtos.UserDto;

public interface UserService {

	/**
	 * Register new Customer
	 * 
	 * @param UserTO user
	 * @return UserTO
	 */
	public abstract UserTO register(UserTO user);
	
	/**
	 * Find by email
	 * 
	 * @param String email
	 * @return UserTO
	 */
	public abstract UserTO findByEmail(String email);
	
	/**
	 * Convert UserDto to UserTO
	 * @param UserDto userDto
	 * @param BindingResult resul
	 * @return UserTO
	 */
	public abstract UserTO convertDtoToUser(UserDto userDto, BindingResult resul) throws NoSuchAlgorithmException;
	
	/**
	 * Convert UserTO to UserDto
	 * @param UserTO user
	 * @return UserDto
	 */
	public abstract UserDto convertUserToDto(UserTO user);
	
}
