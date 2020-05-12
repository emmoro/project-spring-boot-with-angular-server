package com.br.api.product.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtils {

	private static final Logger log = LoggerFactory.getLogger(PasswordUtils.class);

	public PasswordUtils() {
	}

	/**
	 * Generate a hash using BCrypt.
	 * 
	 * @param password
	 * @return String
	 */
	public static String generateBCrypt(String password) {
		if (password == null) {
			return password;
		}

		log.info("Generate hash with BCrypt.");
		BCryptPasswordEncoder bCryptEncoder = new BCryptPasswordEncoder();
		return bCryptEncoder.encode(password);
	}

}
