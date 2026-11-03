package com.example.demo.stock.entity;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity(name = "stock")
@Table(name = "FSK001")
public class MyStock {
	
	private Integer id;
	
	private String symbol;
	
	private String name;
	
	private BigDecimal price;
	
}
