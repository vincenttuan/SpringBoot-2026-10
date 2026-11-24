package com.example.demo.order;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;

import jakarta.transaction.Transactional;

/**
 * 新增商品
 * */
@SpringBootTest
public class Add_Product {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Test
	@Transactional
	@Commit
	public void add() {
		// 建立 2 個商品
		// USB  100元
		// HDMI 200元
		// VGA  50元
		
		Product product1 = new Product();
		product1.setName("USB");
		product1.setPrice(100);
		
		Product product2 = new Product();
		product2.setName("HDMI");
		product2.setPrice(200);
		
		Product product3 = new Product();
		product3.setName("VGA");
		product3.setPrice(50);
		
		// 儲存
		productRepository.save(product1);
		productRepository.save(product2);
		productRepository.save(product3);
		
		System.out.println("product add ok !");
	}
}
