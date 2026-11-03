package com.example.demo.stock;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.stock.entity.MyStock;
import com.example.demo.stock.repository.MyStockRepository;

@SpringBootTest
public class Test_AddMyStock {
	
	@Autowired
	private MyStockRepository myStockRepository;
	
	@Test
	public void add() {
		// 建立 3 個 MyStock
		MyStock ms1 = new MyStock();
		ms1.setSymbol("2330");
		ms1.setName("台積電");
		ms1.setPrice(2295.00);
		
		MyStock ms2 = new MyStock();
		ms2.setSymbol("1101");
		ms2.setName("台泥");
		ms2.setPrice(28.10);
		
		MyStock ms3 = new MyStock();
		ms3.setSymbol("1234");
		ms3.setName("黑松");
		ms3.setPrice(32.35);
		
		myStockRepository.save(ms1);
		myStockRepository.save(ms2);
		myStockRepository.save(ms3);
		
		System.out.println("新增完畢");
		
	}
	
}
