package com.br.api.product.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.br.api.product.entities.UserTO;
import com.br.api.product.repository.UserRepository;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserTO user = userRepository.findByEmail(email);
		if (user != null) {
			return JwtUserFactory.create(user);
		}
		throw new UsernameNotFoundException("Email not found.");
	}

}
