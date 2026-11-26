package com.example.demo.model;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
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
	@OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<OrderItem> items = new ArrayList<>();
	
	// 一張訂單可以指派多位處理人員
	@ManyToMany
	@JoinTable(
			name = "order_staff",
			joinColumns = @JoinColumn(name = "order_id"),
			inverseJoinColumns = @JoinColumn(name = "staff_id"),
			uniqueConstraints = @UniqueConstraint(
					name = "uk_order_staff",
					columnNames = {"order_id", "staff_id"}
			)
	)
	private Set<Staff> staffs = new LinkedHashSet<>();
	
	// 一張訂單最多打一張發票, 尚未結帳時 invoice 是 null
	@OneToOne(mappedBy = "order")
	private Invoice invoice;
	
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
	
	// 將人員加入到訂單
	public void addStaff(Staff staff) {
		staffs.add(staff);
	}
	
	// 將人員從訂單中移出
	public void removeStaff(Staff staff) {
		staffs.remove(staff);
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", status=" + status + ", items=" + items + "]";
	}
	
	
	
}
