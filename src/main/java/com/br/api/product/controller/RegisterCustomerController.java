package com.br.api.product.controller;

import java.security.NoSuchAlgorithmException;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.br.api.product.service.UserService;

import com.br.api.product.entities.UserTO;
import com.br.api.product.model.dtos.UserDto;
import com.br.api.product.response.Response;

@RestController
@RequestMapping("/api/registerCustomer")
@CrossOrigin(origins = "*")
public class RegisterCustomerController {
	
	private static final Logger log = LoggerFactory.getLogger(RegisterCustomerController.class);
	
	@Autowired
	private UserService userService;

	@PostMapping
	public ResponseEntity<Response<UserDto>> register(@Valid @RequestBody UserDto userDto, 
			BindingResult result) throws NoSuchAlgorithmException {
		log.info("Register Customer: {}", userDto.toString());
		Response<UserDto> response = new Response<UserDto>();
		
		UserTO user = this.userService.findByEmail(userDto.getEmail());
		if (user != null) {
			log.error("E-mail already registered");
			response.getErrors().add("E-mail already registered");
			return ResponseEntity.badRequest().body(response);
		}
		
		user = this.userService.convertDtoToUser(userDto, result);
		if (result.hasErrors()) {
			log.error("Error validating register Customer: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		this.userService.register(user);
		response.setData(this.userService.convertUserToDto(user));
		return ResponseEntity.ok(response);
	}
	
}
