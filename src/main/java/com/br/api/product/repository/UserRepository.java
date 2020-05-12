package com.br.api.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.api.product.entities.UserTO;

@Repository
public interface UserRepository extends JpaRepository<UserTO, Long> {

	public UserTO findByEmail(String email);
	
	public UserTO findByUsername(String userName);
	
}
