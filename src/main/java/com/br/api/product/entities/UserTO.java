package com.br.api.product.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "user")
public class UserTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2185104743786065462L;

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@NotNull
	@NotEmpty(message="Mandatory Field")
	private String name;
	
	@NotNull
	@NotEmpty(message="Mandatory Field")
	private String username;
	
	@NotNull
	@NotEmpty(message="Mandatory Field")
	private String password;
	
	@NotNull
	@NotEmpty(message="Mandatory Field")
	private String email;
	
	@NotNull
	@NotEmpty(message="Mandatory Field")
	@Column(name="last_name")
	private String lastName;
	
	private Boolean admin;
	
	@Column(name="create_date")
	private LocalDateTime createDate;
	
	@Transient
	private Collection<? extends GrantedAuthority> authorities;

	public UserTO() {
	}
	
	public UserTO(Long id, String email, String password, Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.authorities = authorities;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "UserTO [id=" + id + ", name=" + name + ", username=" + username + ", password=" + password + ", email="
				+ email + ", lastName=" + lastName + ", admin=" + admin + ", createDate=" + createDate +
				", authorities=" + authorities + "]";
	}

}
