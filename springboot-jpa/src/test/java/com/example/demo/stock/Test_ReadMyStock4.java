package com.example.demo.stock;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.stock.entity.MyStock;
import com.example.demo.stock.repository.MyStockRepository;

@SpringBootTest
public class Test_ReadMyStock4 {
	
	@Autowired
	private MyStockRepository myStockRepository;
	
	@Test
	public void read() {
		// min = 100, max = 200
		BigDecimal min = BigDecimal.valueOf(100.00);
		BigDecimal max = BigDecimal.valueOf(200.00);
		List<MyStock> stocks = myStockRepository.findByPriceBetweenOrderByPriceAsc(min, max);
		System.out.println(stocks);
		
	}
	
	
}
