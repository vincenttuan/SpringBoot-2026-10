package com.example.demo.stock.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Java 16 利用 record 來取代一些 Lombok 的功能
public record MyStockForm(String symbol, String name, BigDecimal price) {}

/*
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyStockForm {
	
	private String symbol;
	private String name;
	private BigDecimal price;
	
}
*/
