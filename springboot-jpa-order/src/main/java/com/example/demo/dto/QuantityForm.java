package com.example.demo.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 修改數量專用
 {
 	"quantity": 3
 }
 * */
@Data
public class QuantityForm {
	
	@NotNull(message = "{quantity.required}")
	@Min(value = 1, message = "{quantity.min}")
	private Integer quantity;
}
