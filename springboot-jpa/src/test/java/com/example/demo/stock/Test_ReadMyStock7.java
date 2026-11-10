package com.example.demo.stock;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import com.example.demo.stock.entity.MyStock;
import com.example.demo.stock.repository.MyStockRepository;
import com.example.demo.stock.service.MyStockService;

@SpringBootTest
public class Test_ReadMyStock7 {
	
	@Autowired
	private MyStockService myStockService;
	
	@Test
	public void read() {
		
		int page = 1;
		int size = 10;
		//Sort sort = Sort.by("id");
		Sort sort = Sort.by(MyStock::getId);
		
		// 得到分頁資訊
		Page<MyStock> myStockPage = myStockService.findPage(page, size, sort);
				
		// getContent() 可以取得這一頁的資料內容
		List<MyStock> myStocks = myStockPage.getContent();
		
		// 印出該頁的資訊
		myStocks.forEach(System.out::println);
		
		// 其他分頁資訊
		System.out.println("目前第幾頁: " + (myStockPage.getNumber() + 1));
		System.out.println("每頁設定筆數: " + myStockPage.getSize());
		System.out.println("本頁實際筆數: " + myStockPage.getNumberOfElements());
		System.out.println("全部資料筆數: " + myStockPage.getTotalElements());
		System.out.println("總頁數: " + myStockPage.getTotalPages());
		
	}
	
	
}
