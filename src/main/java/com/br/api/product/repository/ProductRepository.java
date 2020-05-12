package com.br.api.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.api.product.entities.ProductTO;

@Repository
public interface ProductRepository extends JpaRepository<ProductTO, Long> {

}
