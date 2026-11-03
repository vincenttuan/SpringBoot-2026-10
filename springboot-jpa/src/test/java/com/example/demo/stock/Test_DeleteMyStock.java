package com.example.demo.stock;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.stock.entity.MyStock;
import com.example.demo.stock.repository.MyStockRepository;

@SpringBootTest
public class Test_DeleteMyStock {
	
	@Autowired
	private MyStockRepository myStockRepository;
	
	@Test
	public void delete() {
		// 刪除 id = 2 的紀錄
		Optional<MyStock> optMyStock = myStockRepository.findById(2);
		if(optMyStock.isEmpty()) {
			System.out.println("查無紀錄");
			return;
		}
		
		// 取得要刪除的紀錄
		MyStock ms = optMyStock.get();
		
		// 刪除
		myStockRepository.delete(ms);
		
		System.out.println("刪除成功");
	}
	
}
