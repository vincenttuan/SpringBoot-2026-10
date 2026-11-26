package com.example.demo.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.repository.OrderRepository;

@SpringBootTest
public class Pay_Order {
	
	@Autowired
	private OrderRepository orderRepository;
	
	 
	
}
