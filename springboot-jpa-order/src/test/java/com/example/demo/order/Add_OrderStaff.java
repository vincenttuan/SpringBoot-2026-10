package com.example.demo.order;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Order;
import com.example.demo.model.Staff;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.StaffRepository;

@SpringBootTest
public class Add_OrderStaff {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private StaffRepository staffRepository;
	
	@Test
	@Transactional
	@Commit
	public void add() {
		// 1. 指訂單編號
		Integer orderId = 1;
		
		// 2. 查詢訂單
		Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("查無訂單"));
		
		// 3. 查詢可指派人員
		Staff staff1 = staffRepository.findById(98001).orElseThrow(() -> new RuntimeException("查無人員 98001"));
		Staff staff2 = staffRepository.findById(98002).orElseThrow(() -> new RuntimeException("查無人員 98002"));
		
		// 4. 將訂單存入指派人員
		order.addStaff(staff1);
		order.addStaff(staff2);
		
		//orderRepository.save(order);
		
		System.out.println("order assign staffs ok!");
	}
	
}
