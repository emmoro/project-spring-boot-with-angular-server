package com.br.api.product.controller;

import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.api.product.entities.ProductTO;
import com.br.api.product.model.dtos.ProductDto;
import com.br.api.product.response.Response;
import com.br.api.product.service.ProductService;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductListController {
	
	private static final Logger log = LoggerFactory.getLogger(ProductListController.class);
	
	@Autowired
	private ProductService productService;
	
	@GetMapping(value = "/product/findAllProduct")
	public ResponseEntity<Response<List<ProductDto>>> findAllProducts() {
		
		log.info("Get all products!");
		Response<List<ProductDto>> response = new Response<List<ProductDto>>();

		List<ProductTO> products = this.productService.findAllProducts();
		List<ProductDto> productsDto = products.stream()
				.map(product -> this.productService.convertProductToDto(product))
				.collect(Collectors.toList());

		response.setData(productsDto);
		return ResponseEntity.ok(response);
	}
	
	@PostMapping
	public ResponseEntity<Response<ProductDto>> registerProduct(@Valid @RequestBody ProductDto productDto,
			BindingResult result) throws ParseException, NoSuchAlgorithmException {
		log.info("Add Product: {}", productDto.toString());
		Response<ProductDto> response = new Response<ProductDto>();
		ProductTO produto = this.productService.convertDtoToProduct(productDto, result);

		if (result.hasErrors()) {
			log.error("Error to add new Product: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		produto = this.productService.registerProduct(produto);
		response.setData(this.productService.convertProductToDto(produto));
		return ResponseEntity.ok(response);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Response<ProductDto>> findById(@PathVariable("id") Long id) {
		log.info("Find Product by ID: {}", id);
		Response<ProductDto> response = new Response<ProductDto>();
		Optional<ProductTO> produto = this.productService.findById(id);

		if (!produto.isPresent()) {
			log.info("Product not found by ID: {}", id);
			response.getErrors().add("Product not found by ID " + id);
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(this.productService.convertProductToDto(produto.get()));
		return ResponseEntity.ok(response);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Response<ProductDto>> updateProduct(@PathVariable("id") Long id,
			@Valid @RequestBody ProductDto productDto, BindingResult result) throws ParseException, 
			NoSuchAlgorithmException {
		log.info("Update Product : {}", productDto.toString());
		Response<ProductDto> response = new Response<ProductDto>();
		
		ProductTO produto = this.productService.convertDtoToProduct(productDto, result);
		produto.setId(id);
		if (result.hasErrors()) {
			log.error("Error validating Product: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		produto = this.productService.registerProduct(produto);
		response.setData(this.productService.convertProductToDto(produto));
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Response<String>> remove(@PathVariable("id") Long id) {
		log.info("Remove Product: {}", id);
		Response<String> response = new Response<String>();
		Optional<ProductTO> product = this.productService.findById(id);

		if (!product.isPresent()) {
			log.info("Error removing to Product ID: {} invalid.", id);
			response.getErrors().add("Error removing to Product. Record not found for id " + id);
			return ResponseEntity.badRequest().body(response);
		}

		this.productService.remove(product.get());
		return ResponseEntity.ok(new Response<String>());
	}

}
