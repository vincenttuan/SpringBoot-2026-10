package com.example.demo.stock;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.stock.entity.MyStock;
import com.example.demo.stock.repository.MyStockRepository;

@SpringBootTest
public class Test_ReadMyStock {
	
	@Autowired
	private MyStockRepository myStockRepository;
	
	@Test
	public void read() {
		
		List<MyStock> myStocks = myStockRepository.findAll();
		myStocks.forEach(System.out::println);
		
	}
	
	
}
