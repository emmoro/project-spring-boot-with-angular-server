package com.br.api.product.service.impl;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.br.api.product.entities.UserTO;
import com.br.api.product.model.dtos.UserDto;
import com.br.api.product.repository.UserRepository;
import com.br.api.product.service.UserService;
import com.br.api.product.utils.PasswordUtils;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserTO register(UserTO user) {
		return userRepository.saveAndFlush(user);
	}
	
	@Override
	public UserTO findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	@Override
	public UserTO convertDtoToUser(UserDto userDto, BindingResult resul) throws NoSuchAlgorithmException {
		UserTO user = new UserTO();
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setLastName(userDto.getLastName());
		user.setUsername(userDto.getUsername());
		user.setPassword(PasswordUtils.generateBCrypt(userDto.getPassword()));
		user.setAdmin(false);
		user.setCreateDate(LocalDateTime.now());

		return user;
	}
	
	@Override
	public UserDto convertUserToDto(UserTO user) {
		UserDto userDto = new UserDto();
		userDto.setName(user.getName());
		userDto.setEmail(user.getEmail());
		userDto.setLastName(user.getLastName());
		userDto.setUsername(user.getUsername());

		return userDto;
	}

}
