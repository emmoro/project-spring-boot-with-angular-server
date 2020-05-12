package com.br.api.product.repository;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.br.api.product.entities.ProductTO;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class ProductRepositoryTest {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Test
	public void shouldRegisterProduct() {
		ProductTO product = new ProductTO();
		product.setDescription("Test to create");
		product.setDetails("Create a new product");
		product.setUrlSite("https://test.com/");
		
		this.productRepository.saveAndFlush(product);
		Assertions.assertThat(product.getId()).isNotNull();
	}
	
	@Test
	public void shouldfindAllProducts() {
		ProductTO product = new ProductTO();
		product.setDescription("Test to create");
		product.setDetails("Create a new product");
		product.setUrlSite("https://test.com/");
		
		ProductTO product2 = new ProductTO();
		product2.setDescription("Test to create 2");
		product2.setDetails("Create a new product 2");
		product2.setUrlSite("https://test2.com/");
		
		this.productRepository.saveAndFlush(product);
		this.productRepository.saveAndFlush(product2);
		
		List<ProductTO> listProducts = this.productRepository.findAll();
		Assertions.assertThat(listProducts.size() > 2);
	}
	
	@Test
	public void shouldFindById() {
		ProductTO product = new ProductTO();
		product.setDescription("Test to create");
		product.setDetails("Create a new product");
		product.setUrlSite("https://test.com/");

		this.productRepository.saveAndFlush(product);
		Optional<ProductTO> prod = this.productRepository.findById(product.getId());
		Assertions.assertThat(prod.get().getId()).isNotNull();
	}
	
	@Test
	public void shouldRemoveProduct() {
		ProductTO product = new ProductTO();
		product.setDescription("Test to create");
		product.setDetails("Create a new product");
		product.setUrlSite("https://test.com/");

		this.productRepository.saveAndFlush(product);
		this.productRepository.delete(product);
		Assertions.assertThat(productRepository.findById(product.getId())).isNotNull();
	}

}
