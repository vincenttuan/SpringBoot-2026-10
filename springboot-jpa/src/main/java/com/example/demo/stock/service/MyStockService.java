package com.example.demo.stock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.stock.entity.MyStock;
import com.example.demo.stock.repository.MyStockRepository;

@Service
public class MyStockService {
	
	@Autowired
	private MyStockRepository myStockRepository;
	
	// 未分頁查詢
	public List<MyStock> findAll() {
		return myStockRepository.findAll();
	}
	
	// 分頁查詢: page 頁碼, size 每頁筆數, sort 排序欄位
 	public Page<MyStock> findPage(int page, int size, Sort sort) {
		// 判斷頁碼與筆數是否正確
		if(page < 1) {
			page = 1;
		}
		
		if(size < 1) {
			size = 10;
		}
		
		// 畫面第 1 頁, 要轉成 PageRequest 的第 0 頁
		// 畫面第 2 頁, 要轉成 PageRequest 的第 1 頁
		// Pageable 分頁查詢條件
		Pageable pageable = PageRequest.of(page - 1, size, sort);
		
		// 查詢指定頁面同時取得相關資訊
		// Page 分頁查詢結果
		Page<MyStock> myStockPage = myStockRepository.findAll(pageable);
		
		return myStockPage;
	}
	
}
