package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * 商品檔
 * */

@Getter
@Setter
@Entity
@Table(name = "product")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(comment = "商品編號")
	private Integer id; // 商品編號
	
	@Column(comment = "商品名稱", length = 50, nullable = false)
	private String name; // 商品名稱
	
	@Column(comment = "商品價格", nullable = false)
	private Integer price; // 商品價格
	
}


