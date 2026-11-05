package com.example.demo.stock;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;

import com.example.demo.stock.entity.MyStock;
import com.example.demo.stock.repository.MyStockRepository;

@SpringBootTest
public class Test_ReadMyStock5 {
	
	@Autowired
	private MyStockRepository myStockRepository;
	
	@Test
	public void read() {
		
		List<MyStock> stocks = myStockRepository.findByPriceBetween(min, max, sort1);
		stocks.forEach(System.out::println);
		
		
	}
	
	
}
