package com.example.demo.order;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import com.example.demo.model.Staff;
import com.example.demo.repository.StaffRepository;

import jakarta.transaction.Transactional;

/**
 * 儲存處理人員
 * */
@SpringBootTest
public class Add_Staff {
	
	@Autowired
	private StaffRepository staffRepository;
	
	@Test // 執行 JUnit 執行此程式
	@Transactional // 將多筆 save() 放在同一個交易裡面
	@Commit // 因為交易測試預設會回滾, 這裡改為提交, 就可以將資料存入到資料表中
	public void add() {
		// 創造 2 位員工
		Staff staff1 = new Staff();
		staff1.setId(98001);
		staff1.setName("王小明");
		
		Staff staff2 = new Staff();
		staff2.setId(98002);
		staff2.setName("林小美");
		
		// 儲存
		staffRepository.save(staff1);
		staffRepository.save(staff2);
		
		System.out.println("staff add ok !");
	}
	
}
