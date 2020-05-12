package com.br.api.product.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.br.api.product.entities.UserTO;
import com.br.api.product.utils.PasswordUtils;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UserRepositoryTest {
	
	@Autowired
	private UserRepository userRepository;
	
	@Test
	public void createShouldUser() {
		UserTO user = new UserTO();
		user.setAdmin(true);
		user.setEmail("test@test.com");
		user.setUsername("test");
		user.setName("Test");
		user.setLastName("testing");
		user.setPassword(PasswordUtils.generateBCrypt("123456"));
		
		this.userRepository.saveAndFlush(user);
		Assertions.assertThat(user.getId()).isNotNull();
		Assertions.assertThat(user.getEmail()).isEqualTo("test@test.com");
	}
	
	@Test
	public void findByEmail() {
		UserTO user = new UserTO();
		user.setAdmin(true);
		user.setEmail("test@test.com");
		user.setUsername("test");
		user.setName("Test");
		user.setLastName("testing");
		user.setPassword(PasswordUtils.generateBCrypt("123456"));
		
		this.userRepository.saveAndFlush(user);
		UserTO findUser = userRepository.findByEmail(user.getEmail());
		Assertions.assertThat(findUser.getEmail()).isEqualTo("test@test.com");
	}

}
