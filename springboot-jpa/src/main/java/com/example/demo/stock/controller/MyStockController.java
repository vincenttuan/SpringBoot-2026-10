package com.example.demo.stock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.stock.service.MyStockService;

@Controller
@RequestMapping("/mystock")
public class MyStockController {
	
	@Autowired
	private MyStockService myStockService;
	
	@GetMapping
	public String findAll(Model model) {
		
		
		return "mystock/list";
	}
	
	
}
