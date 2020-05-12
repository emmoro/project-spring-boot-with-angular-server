package com.br.api.product.security.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import com.br.api.product.entities.UserTO;
import com.br.api.product.enums.ProfileEnum;

public class JwtUserFactory {

	private JwtUserFactory() {
	}

	/**
	 * Converts and generates a JwtUser based on a user's data.
	 * 
	 * @param user
	 * @return JwtUser
	 */
	public static UserDetails create(UserTO user) {
		return new User(user.getUsername(), user.getPassword(), 
				mapToGrantedAuthorities(user.getAdmin()));
	}

	/**
	 * Converts the user's profile to the format used by Spring Security.
	 * 
	 * @param perfilEnum
	 * @return List<GrantedAuthority>
	 */
	private static List<GrantedAuthority> mapToGrantedAuthorities(Boolean admin) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(
				(admin ? ProfileEnum.ROLE_ADMIN.toString() : ProfileEnum.ROLE_USER.toString())));
		return authorities;
	}

}
