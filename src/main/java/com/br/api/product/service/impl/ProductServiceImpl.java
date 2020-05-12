package com.br.api.product.service.impl;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.br.api.product.entities.ProductTO;
import com.br.api.product.model.dtos.ProductDto;
import com.br.api.product.repository.ProductRepository;
import com.br.api.product.service.ProductService;

@Service("productService")
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<ProductTO> findAllProducts() {
		return productRepository.findAll();
	}
	
	@Override
	public ProductTO registerProduct(ProductTO product) {
		return productRepository.saveAndFlush(product);
	}
	
	@Override
	public Optional<ProductTO> findById(Long id) {
		return productRepository.findById(id);
	}

	@Override
	public void remove(ProductTO product) {
		productRepository.delete(product);
	}
	
	@Override
	public ProductTO convertDtoToProduct(ProductDto productDto, BindingResult resul) 
			throws NoSuchAlgorithmException {
		ProductTO product = new ProductTO();
		product.setDescription(productDto.getDescription());
		product.setDetails(productDto.getDetails());
		product.setUrlSite(productDto.getUrlSite());

		return product;
	}
	
	@Override
	public ProductDto convertProductToDto(ProductTO product) {
		ProductDto productDto = new ProductDto();
		productDto.setId(Optional.of(product.getId()));
		productDto.setDescription(product.getDescription());
		productDto.setDetails(product.getDetails());
		productDto.setUrlSite(product.getUrlSite());

		return productDto;
	}
	
}
