package com.example.demo.stock;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.stock.entity.MyStock;
import com.example.demo.stock.repository.MyStockRepository;

@SpringBootTest
public class Test_ReadMyStock2 {
	
	@Autowired
	private MyStockRepository myStockRepository;
	
	@Test
	public void read() {
		// 國鉅 2327
		Optional<MyStock> stock = myStockRepository.fi
		
		
	}
	
	
}
