package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Order;
import com.example.demo.repository.InvoiceRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.StaffRepository;

@Service
@Transactional // 所有方法都會加入 @Transactional
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private StaffRepository staffRepository;
	
	@Autowired
	private InvoiceRepository invoiceRepository;
	
	// 建立空訂單(空訂單 = 沒有明細), 回傳訂單編號
	public Integer createEmpty() {
		Order order = new Order();
		//orderRepository.save(order);
		//return order.getId();
		Order savedOrder = orderRepository.save(order);
		return savedOrder.getId();
	}
	
	
	
}
