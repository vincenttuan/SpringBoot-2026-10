package com.example.demo.stock;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import com.example.demo.stock.entity.MyStock;
import com.example.demo.stock.repository.MyStockRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
public class Test_UpdateMyStock2 {
	
	@Autowired
	private MyStockRepository myStockRepository;
	
	@Test
	@Transactional // 交易模式
	@Commit // 因為在測試環境中的交易模式完畢後會自動 rollback 還原狀態
	        // @Commit 表示手動介入提交, 會讓測試環境中自動 rollback 自動取消
	public void update() {
		// 更新台積電價格 => 2280.00
		// 找到台積電
		MyStock ms = myStockRepository.findById(1).get();
		
		// 更新價格
		ms.setPrice(2500);
		
		// 回存
		//myStockRepository.save(ms);
		
		System.out.println("全自動更新完畢");
		
	}
	
}
