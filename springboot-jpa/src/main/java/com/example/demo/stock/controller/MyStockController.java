package com.example.demo.stock.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.stock.entity.MyStock;
import com.example.demo.stock.service.MyStockService;

@Controller
@RequestMapping("/mystock")
public class MyStockController {
	
	@Autowired
	private MyStockService myStockService;
	
	/*
	 * URL:
	 * /mystock
	 * /mystock?page=1&size=10&sort=id&dir=asc 前 10 筆
	 * 
	 * sort => 排序欄位
	 * dir  => 排序方向(desc, asc)
	 * 
	 * */
	@GetMapping
	public String findAll(Model model) {
		// 取得所有資料
		List<MyStock> myStocks = myStockService.findAll();
		
		// 將 myStocks 資料放入到 model 物件屬性中, 目的是給 list.html 存取之用
		model.addAttribute("myStocks", myStocks);
		
		return "mystock/list";
	}
	
	
}
