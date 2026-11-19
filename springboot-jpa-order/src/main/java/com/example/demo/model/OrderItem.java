package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * 訂單明細
 * */

@Getter
@Setter
@Entity
@Table(name = "order_item")
public class OrderItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(comment = "明細編號")
	private Integer id;
	
	@Column(comment = "購買數量", nullable = false)
	private Integer quantity;
	
	@Column(comment = "購買單價", name = "unit_price", nullable = false)
	private Integer unitPrice;
	
	@ManyToOne
	@JoinColumn(name = "order_id", nullable = false, comment = "訂單編號")
	private Order order;
	
	
}
