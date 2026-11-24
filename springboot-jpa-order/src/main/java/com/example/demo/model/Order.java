package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "`order`")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(comment = "訂單編號")
	private Integer id;
	
	@Enumerated(EnumType.STRING)
	@Column(comment = "訂單狀態", nullable = false)
	private OrderStatus status = OrderStatus.PENDING_PAYMENT;
	
	/**
	 * mappedBy = "order": 由 OrderItem.order 來維護關係
	 * CascadeType.PERSIST: 新增訂單時連帶新增明細(聯集新增)
	 * orphanRemoval = true: 移除關聯時將明細一併刪除
	 * */
	@OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST, orphanRemoval = true)
	private List<OrderItem> items = new ArrayList<>();
	
	// 新增 item 雙向同步
	public void addItem(OrderItem orderItem) {
		orderItem.setOrder(this);
		items.add(orderItem);
	}
	
	// 移除 item 雙向同步
	public void removeItem(OrderItem orderItem) {
		orderItem.setOrder(null);
		items.remove(orderItem);
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", status=" + status + ", items=" + items + "]";
	}
	
	
	
}
