package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {
	
	// 1. Get 直接存取
	// 路徑: /api/hello
	@GetMapping("/hello")
	public String hello() {
		return "Hello";
	}
	
	// 路徑: /api/welcome
	@GetMapping("/welcome")
	public String welcome() {
		return "Welcome";
	}
	
	/*
	 * 2. Get + ?參數
	 * QueryString
	 * 路徑: /api/greet?name=John
	 * 結果: Greet John
	 * 
	 * 路徑: /api/greet?name=Mary
	 * 結果: Greet Mary
	 */
	
	
	
	
}
