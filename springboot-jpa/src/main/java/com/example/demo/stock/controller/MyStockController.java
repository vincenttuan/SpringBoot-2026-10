package com.example.demo.stock.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	public String findAll(Model model,
						  @RequestParam(required = false, defaultValue = "1") int page,
						  @RequestParam(required = false, defaultValue = "10") int size,
						  @RequestParam(required = false, defaultValue = "id") String sort,
						  @RequestParam(required = false, defaultValue = "asc") String dir) {
		
		// 排序方向
		Sort.Direction direction = dir.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
		
		// 建立排序物件
		Sort myStockSort = Sort.by(direction, sort);
		
		// 取得所有資料
		//List<MyStock> myStocks = myStockService.findAll();
		List<MyStock> myStocks = myStockService.findPage(page, size, myStockSort).getContent();
		
		// 將 myStocks 資料放入到 model 物件屬性中, 目的是給 list.html 存取之用
		model.addAttribute("myStocks", myStocks);
		
		return "mystock/list";
	}
	
	
}
