package com.br.api.product.service;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

import org.springframework.validation.BindingResult;

import com.br.api.product.entities.ProductTO;
import com.br.api.product.model.dtos.ProductDto;

public interface ProductService {
	
	/**
	 * Find all products by email
	 * 
	 * @return List<ProductTO>
	 */
	public abstract List<ProductTO> findAllProducts();
	
	/**
	 * Find by Id
	 * @param Long id
	 * @return Optional<ProductTO>
	 */
	public abstract Optional<ProductTO> findById(Long id);
	
	/**
	 * Register new Product
	 * @param ProductTO product
	 * @return ProductTO
	 */
	public abstract ProductTO registerProduct(ProductTO product);
	
	/**
	 * Delete Product
	 * @param ProductTO product
	 * @return Optional<ProductTO>
	 */
	public abstract void remove(ProductTO product);
	
	/**
	 * Convert ProductDto to Product
	 * @param ProductDto productDto
	 * @param BindingResult resul
	 * @return ProductTO
	 */
	public abstract ProductTO convertDtoToProduct(ProductDto productDto, BindingResult resul) 
			throws NoSuchAlgorithmException;
	
	/**
	 * Convert ProductTO to ProductDto
	 * @param ProductTO product
	 * @return ProductDto
	 */
	public abstract ProductDto convertProductToDto(ProductTO product);

}
