package com.example.demo.stock.entity;

import java.math.BigDecimal;
import java.math.RoundingMode;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity(name = "stock")
@Table(name = "my_stock")
public class MyStock {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "symbol", length = 10, nullable = false, unique = true)
	private String symbol;
	
	@Column(name = "name", length = 50, nullable = false, unique = true)
	private String name;
	
	// MySQL 資料欄位屬性定義: DECIMAL(12, 2) 最多10位整數 + 2位小數
	@Column(name = "price", precision = 12, scale = 2)
	private BigDecimal price;
	
	public void setPrice(BigDecimal price) {
		// RoundingMode.HALF_UP   四捨五入
		// RoundingMode.HALF_DOWN 五捨六入
		// RoundingMode.HALF_EVEN 四捨六入五成雙
		// 小數點保持 2 位, 第三位起採四捨五入
		this.price = price.setScale(2, RoundingMode.HALF_UP);
	}
	
	public void setPrice(double price) {
		// BigDecimal.valueOf(price): 將 double 轉 BigDecimal 物件
		setPrice(BigDecimal.valueOf(price));
	}
	
}
