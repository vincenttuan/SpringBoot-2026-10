package com.example.demo.dto;

import lombok.Data;

/**
 * 結帳專用
  {
    "invoiceNo": "GH00000001"
  }
 * */
@Data
public class CheckoutForm {
	
	private String invoiceNo;
}
