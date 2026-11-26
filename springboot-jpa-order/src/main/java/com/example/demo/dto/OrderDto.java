package com.example.demo.dto;

import java.util.List;

import com.example.demo.model.OrderItem;
import com.example.demo.model.OrderStatus;
import com.example.demo.model.Staff;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 這個 DTO 只負責回傳資料，不負責操作資料庫。
 * 
  {
    "id": 4,
    "status": "PAID",
    "total": 600,
    "invoiceNo": "CH00000001",
    "items": [
      {
        "id": 6,
        "productId": 1,
        "productName": "USB線",
        "quantity": 4,
        "unitPrice": 100,
        "subTotal": 400
      },
      {
        "id": 7,
        "productId": 2,
        "productName": "HDMI線",
        "quantity": 1,
        "unitPrice": 200,
        "subTotal": 200
      }
    ],
    "staffs": [
      {
        "id": 98001,
        "name": "王小明"
      },
      {
        "id": 98002,
        "name": "林小美"
      }
    ]
  }
 * 
 * */
@Data
@AllArgsConstructor
public class OrderDto {
	
	private Integer id;
	private OrderStatus status;
	private Long total;
	private String invoiceNo;
	private List<OrderItem> items;
	private List<Staff> staffs;
	
	// 定義明細的回傳格式
	@Data
	@AllArgsConstructor
	public static class ItemDto {
		private Integer id;
		private Integer productId;
		private String productName;
		private Integer quantity;
		private Integer unitPrice;
		private Long subTotal;
	}
	
	// 定義人員回傳格式
	@Data
	@AllArgsConstructor
	public static class StaffDto {
		private Integer id;
		private String name;
	}
}
