package com.br.api.product.model.dtos;

import java.util.Optional;

import javax.validation.constraints.NotEmpty;

public class ProductDto {
	
	private Optional<Long> id = Optional.empty();
	
	private String description;
	
	private String details;
	
	private String urlSite;

	public Optional<Long> getId() {
		return id;
	}

	public void setId(Optional<Long> id) {
		this.id = id;
	}

	@NotEmpty(message = "Description cannot be empty.")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@NotEmpty(message = "Details cannot be empty.")
	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	@NotEmpty(message = "Url Site cannot be empty.")
	public String getUrlSite() {
		return urlSite;
	}

	public void setUrlSite(String urlSite) {
		this.urlSite = urlSite;
	}

	@Override
	public String toString() {
		return "ProductDto [id=" + id + ", description=" + description + ", details=" 
				+ details + ", urlSite=" + urlSite + "]";
	}

}
