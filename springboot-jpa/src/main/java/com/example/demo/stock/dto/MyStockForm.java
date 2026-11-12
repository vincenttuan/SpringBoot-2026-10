package com.example.demo.stock.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Java 16 利用 record 來取代一些 Lombok 的功能
public record MyStockForm(
		
		// 股票代號
		//@NotBlank(message = "股票代號不可空白")
		@NotBlank(message = "{mystock.symbol.notBlank}")
		String symbol,
		
		String name,
		
		BigDecimal price) {}

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
