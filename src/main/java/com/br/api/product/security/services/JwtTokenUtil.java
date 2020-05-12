package com.br.api.product.security.services;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.br.api.product.entities.UserTO;
import com.br.api.product.repository.UserRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil {

	static final String CLAIM_KEY_USERNAME = "sub";
	static final String CLAIM_KEY_ROLE = "role";
	static final String CLAIM_KEY_AUDIENCE = "audience";
	static final String CLAIM_KEY_CREATED = "created";

	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.expiration}")
	private Long expiration;

	@Autowired
	private UserRepository userRepository;

	/**
	 * Gets the username (email) contained in the JWT token.
	 * 
	 * @param token
	 * @return String
	 */
	public String getUsernameFromToken(String token) {
		String username;
		try {
			Claims claims = getClaimsFromToken(token);
			username = claims.getSubject();
		} catch (Exception e) {
			username = null;
		}
		return username;
	}

	/**
	 * Returns the expiration date of a JWT token.
	 * 
	 * @param token
	 * @return Date
	 */
	public Date getExpirationDateFromToken(String token) {
		Date expiration;
		try {
			Claims claims = getClaimsFromToken(token);
			expiration = claims.getExpiration();
		} catch (Exception e) {
			expiration = null;
		}
		return expiration;
	}

	/**
	 * Create a new token (refresh).
	 * 
	 * @param token
	 * @return String
	 */
	public String refreshToken(String token) {
		String refreshedToken;
		try {
			Claims claims = getClaimsFromToken(token);
			claims.put(CLAIM_KEY_CREATED, new Date());
			refreshedToken = generateToken(claims);
		} catch (Exception e) {
			refreshedToken = null;
		}
		return refreshedToken;
	}

	/**
	 * Checks and returns whether a JWT token is valid.
	 * 
	 * @param token
	 * @return boolean
	 */
	public boolean validToken(String token) {
		return !tokenExpired(token);
	}

	/**
	 * Returns a new JWT token based on user data.
	 * 
	 * @param userDetails
	 * @return String
	 */
	public String doGenerateToken(UserDetails userDetails) {
		UserTO user = userRepository.findByUsername(userDetails.getUsername());
		Map<String, Object> claims = new HashMap<>();
		claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
		userDetails.getAuthorities().forEach(authority -> claims.put(CLAIM_KEY_ROLE, authority.getAuthority()));
		claims.put(CLAIM_KEY_CREATED, new Date());
		if (user != null) {
			claims.put("id", user.getId());
		}

		return generateToken(claims);
	}

	/**
	 * Parse the JWT token to extract the information contained in its body.
	 * 
	 * @param token
	 * @return Claims
	 */
	private Claims getClaimsFromToken(String token) {
		Claims claims;
		try {
			claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		} catch (Exception e) {
			claims = null;
		}
		return claims;
	}

	/**
	 * Returns the expiration date based on the current date.
	 * 
	 * @return Date
	 */
	private Date generateDateExpiracao() {
		return new Date(System.currentTimeMillis() + expiration * 1000);
	}

	/**
	 * Checks whether a JTW token is expired.
	 * 
	 * @param token
	 * @return boolean
	 */
	private boolean tokenExpired(String token) {
		Date dateExpiracao = this.getExpirationDateFromToken(token);
		if (dateExpiracao == null) {
			return false;
		}
		return dateExpiracao.before(new Date());
	}

	/**
	 * Generate new Token JWT with datas the (claims).
	 * 
	 * @param claims
	 * @return String
	 */
	private String generateToken(Map<String, Object> claims) {
		return Jwts.builder().setClaims(claims).setExpiration(generateDateExpiracao())
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}

}
