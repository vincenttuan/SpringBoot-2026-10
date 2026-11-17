package com.example.demo.stock.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Java 16 利用 record 來取代一些 Lombok 的功能
public record MyStockForm(
		
		// 股票代號
		//@NotBlank(message = "股票代號不可空白")
		@NotBlank(message = "{mystock.symbol.notBlank}") // 不可空白
		@Pattern(regexp = "^[A-Za-z0-9]+$", message = "{mystock.symbol.pattern}") // 必須是英數字
		@Size(min = 4, max = 10, message = "{mystock.symbol.size}") // 字數限制
		String symbol,
		
		@NotBlank(message = "{mystock.name.notBlank}") // 不可空白
		@Size(max = 0, message = "{mystock.name.size}") // 字數限制
		String name,
		
		@NotNull(message = "{mystock.price.notNull}") // 不可空值
		@DecimalMin(value = "0.01", message = "{mystock.price.min}") // 最小數值限制
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
