package com.example.demo.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 商品細目
 {
 	"productId": 1,
 	"quantity": 2
 } 
 * */
@Data
public class OrderItemForm {
	
	@NotNull(message = "{productId.required}")
	@Min(value = 1, message = "{productId.min}")
	private Integer productId;
	
	@NotNull(message = "{quantity.required}")
	@Min(value = 1, message = "{quantity.min}")
	private Integer quantity;
	
}
