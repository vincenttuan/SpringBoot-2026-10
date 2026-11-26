package com.example.demo.dto;

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
public class OrderDto {

}
