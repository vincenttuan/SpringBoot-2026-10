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
public class Test_ReadMyStock6 {
	
	@Autowired
	private MyStockRepository myStockRepository;
	
	@Test
	public void read() {
		String symbol = "2330";
		boolean exists = myStockRepository.existsBySymbol(symbol);
		System.out.printf("%s => %b%n", symbol, exists);
		
		long count = myStockRepository.existsBySymbol_TSQL(symbol);
		System.out.printf("%s => %b%n", symbol, (count == 1));
		
		
		
	}
	
	
}
