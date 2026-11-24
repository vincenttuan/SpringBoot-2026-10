package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	// 根據名稱查找商品
	Optional<Product> findByName(String name);
	
}
