package com.example.demo.order;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Order;
import com.example.demo.repository.OrderRepository;

@SpringBootTest
public class Read_Order {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Test
	@Transactional(readOnly = true)
	public void read() {
		
		//List<Order> orders = orderRepository.findAll();
		List<Order> orders = orderRepository.findAllWithItems();
		
		orders.forEach(order -> {
			System.out.printf("orderId: %s status: %s items: %s%n", 
					order.getId(), order.getStatus(), order.getItems());
		});
		
	}
	
}
