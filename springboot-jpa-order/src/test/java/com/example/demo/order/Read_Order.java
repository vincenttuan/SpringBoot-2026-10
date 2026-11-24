package com.example.demo.order;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.model.Order;
import com.example.demo.repository.OrderRepository;

@SpringBootTest
public class Read_Order {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Test
	public void read() {
		
		List<Order> orders = orderRepository.findAll();
		
		orders.forEach(order -> {
			System.out.println(order);
		});
		
	}
	
}
