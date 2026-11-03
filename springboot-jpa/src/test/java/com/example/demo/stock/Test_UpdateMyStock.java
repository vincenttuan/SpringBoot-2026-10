package com.example.demo.stock;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.stock.entity.MyStock;
import com.example.demo.stock.repository.MyStockRepository;

@SpringBootTest
public class Test_UpdateMyStock {
	
	@Autowired
	private MyStockRepository myStockRepository;
	
	@Test
	public void update() {
		// 更新台積電價格 => 2280.00
		// 找到台積電
		MyStock ms = myStockRepository.findById(1).get();
		
		// 更新價格
		ms.setPrice(2280);
		
		// 回存
		myStockRepository.save(ms);
		
		System.out.println("更新完畢");
		
	}
	
}
