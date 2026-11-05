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
public class Test_ReadMyStock4 {
	
	@Autowired
	private MyStockRepository myStockRepository;
	
	@Test
	public void read() {
		// min = 100, max = 200
		BigDecimal min = BigDecimal.valueOf(100.00);
		BigDecimal max = BigDecimal.valueOf(200.00);
		//List<MyStock> stocks = myStockRepository.findByPriceBetweenOrderByPriceAsc(min, max);
		//List<MyStock> stocks = myStockRepository.findByPriceBetweenOrderByPriceDesc(min, max);
		
		Sort sort1 = Sort.by("price").ascending();
		Sort sort2 = Sort.by("price").descending();
		Sort sort3 = Sort.by("id").ascending();
		Sort sort4 = Sort.by("id").descending();
		Sort sort5 = Sort.by("symbol").ascending();
		Sort sort6 = Sort.by("symbol").descending();
		List<MyStock> stocks = myStockRepository.findByPriceBetween(min, max, sort1);
		stocks.forEach(System.out::println);
		
		
	}
	
	
}
