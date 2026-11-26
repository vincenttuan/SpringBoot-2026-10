package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

/**
 * 結帳專用
  {
    "invoiceNo": "GH00000001"
  }
 * */
@Data
public class CheckoutForm {
	
	@NotBlank(message = "{invoiceNo.required}")
	@Pattern(
		regexp = "[A-Z]{2}[0-9]{8}",
		message = "{invoiceNo.format}"
	)
	private String invoiceNo;
}
