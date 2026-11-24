package com.example.demo.order;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import com.example.demo.model.Order;
import com.example.demo.model.OrderItem;
import com.example.demo.model.OrderStatus;
import com.example.demo.model.Product;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.ProductRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
public class Add_Order {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Test
	@Transactional
	@Commit
	public void add() {
		// 可訂購商品
		Product usb = productRepository.findByName("USB").get();
		Product hdmi = productRepository.findByName("HDMI").get();
		Product vga = productRepository.findByName("VGA").get();
		
		// 建立明細
		OrderItem item1 = new OrderItem();
		item1.setProduct(usb); // 商品
		item1.setUnitPrice(usb.getPrice()); // 單位價格
		item1.setQuantity(1); // 數量
		
		OrderItem item2 = new OrderItem();
		item2.setProduct(hdmi); // 商品
		item2.setUnitPrice(hdmi.getPrice()); // 單位價格
		item2.setQuantity(2); // 數量
		
		// 建立訂單
		Order order = new Order();
		order.setStatus(OrderStatus.PENDING_PAYMENT);
		
		// 建立關聯關係
		item1.setOrder(order);
		item2.setOrder(order);
		order.getItems().add(item1);
		order.getItems().add(item2);
		
		// 儲存
		orderRepository.save(order);
		
		System.out.println("order & item add ok");
	}
	
}
