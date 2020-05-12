package com.br.api.product.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.br.api.product.entities.ProductTO;
import com.br.api.product.model.dtos.ProductDto;
import com.br.api.product.repository.ProductRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ProductListControllerTest {
	
	private static final String URL_BASE = "/api/products";
	private static final String FIND_ALL = "/product/findAllProduct";
	
	@MockBean
	private ProductRepository productRepository;
	
	@Autowired
	private MockMvc mockMvc;
	
	private ProductTO product;
	
	@BeforeEach
	public void setup() {
		product = new ProductTO();
		product.setId(1L);
		product.setDescription("Test to create");
		product.setDetails("Create a new product");
		product.setUrlSite("https://test.com/");
	}
	
	@Test
	@WithMockUser
	public void testRegisterProduct() throws Exception {
		BDDMockito.given(this.productRepository.findById(Mockito.anyLong())).willReturn(Optional.of(new ProductTO()));
		BDDMockito.given(this.productRepository.saveAndFlush(Mockito.any(ProductTO.class))).willReturn(product);

		mockMvc.perform(MockMvcRequestBuilders.post(URL_BASE)
				.content(this.convertProductToDto())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.errors").isEmpty());
	}
	
	@Test
	@WithMockUser
	public void testFindAllProduct() throws Exception {
		ProductTO product2 = registerSecondProduct();
		List<ProductTO> listProduct = Arrays.asList(product, product2);
		BDDMockito.when(this.productRepository.findAll()).thenReturn(listProduct);

		mockMvc.perform(MockMvcRequestBuilders.get(URL_BASE + FIND_ALL)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.errors").isEmpty());
	}
	
	@Test
	@WithMockUser
	public void testUpdateProduct() throws Exception {
		BDDMockito.given(this.productRepository.findById(Mockito.anyLong())).willReturn(Optional.of(new ProductTO()));
		BDDMockito.given(this.productRepository.saveAndFlush(Mockito.any(ProductTO.class))).willReturn(product);
		product.setDescription("Test to create 4");
		
		mockMvc.perform(MockMvcRequestBuilders.put(URL_BASE + "/" + product.getId())
				.content(this.convertProductToDto())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.errors").isEmpty());
	}
	
	@Test
	@WithMockUser
	public void testRemoveProduct() throws Exception {
		BDDMockito.given(this.productRepository.saveAndFlush(Mockito.any(ProductTO.class))).willReturn(product);
		BDDMockito.given(this.productRepository.findById(Mockito.anyLong())).willReturn(Optional.of(new ProductTO()));

		mockMvc.perform(MockMvcRequestBuilders.delete(URL_BASE + "/" + product.getId())
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
	@Test
	@WithMockUser
	public void errorRegisterProduct() throws Exception {
		BDDMockito.given(this.productRepository.findById(Mockito.anyLong())).willReturn(Optional.of(new ProductTO()));
		BDDMockito.given(this.productRepository.saveAndFlush(Mockito.any(ProductTO.class))).willReturn(product);
		product.setDescription(null);
		
		mockMvc.perform(MockMvcRequestBuilders.post(URL_BASE)
				.content(this.convertProductToDto())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}
	
	private String convertProductToDto() throws JsonProcessingException {
		ProductDto productDto = new ProductDto();
		productDto.setId(null);
		productDto.setDescription(product.getDescription());
		productDto.setDetails(product.getDetails());
		productDto.setUrlSite(product.getUrlSite());

		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(productDto);
	}
	
	private ProductTO registerSecondProduct() {
		ProductTO product2 = new ProductTO();
		product2.setId(2L);
		product2.setDescription("Test to create2");
		product2.setDetails("Create a new product2");
		product2.setUrlSite("https://test2.com/");
		
		return product2;
	}

}
