package com.br.api.product.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import com.br.api.product.entities.UserTO;
import com.br.api.product.repository.UserRepository;
import com.br.api.product.utils.PasswordUtils;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class RegisterCustomerControllerTest {
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@MockBean
	private UserRepository userRepository;
	
	private UserTO user;
	
	@BeforeEach
	public void setup() {
		user = new UserTO();
		user.setAdmin(true);
		user.setEmail("testing@test.com");
		user.setUsername("test");
		user.setName("Test");
		user.setLastName("testing");
		user.setPassword(PasswordUtils.generateBCrypt("123456"));
	}
	
	@Test
	public void getUserShouldReturnStatusCode200() throws Exception {
		ResponseEntity<String> response = restTemplate.exchange("/api/registerCustomer", HttpMethod.POST, 
				new HttpEntity<>(user, null), String.class);
		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
	}

	@Test
	public void getUserShouldReturnStatusCode400() throws Exception {
		BDDMockito.when(userRepository.findByEmail(user.getEmail())).thenReturn(user);
		ResponseEntity<String> response = restTemplate.exchange("/api/registerCustomer", HttpMethod.POST, 
				new HttpEntity<>(user, null), String.class);
		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(400);
	}

}
