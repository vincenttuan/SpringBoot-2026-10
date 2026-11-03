package com.example.demo.stock.entity;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity(name = "stock")
@Table(name = "FSK001")
public class MyStock {
	
	@Id
	private Integer id;
	
	private String symbol;
	
	private String name;
	
	private BigDecimal price;
	
}
