package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	@GetMapping("/greet")
	public String greet(@RequestParam(value = "name", required = false, defaultValue = "no name") String username) {
		return "Greet " + username;
	}
	
	// 路徑: /api/hi?name=Rose
	@GetMapping("/hi")
	public String hi(@RequestParam String name) {
		return "Hi " + name;
	}
	
	/**
	 * 3.多參數處理
	 * 路徑: /api/old?name=Jack&age=20
	 * 結果: "姓名:Jack 年齡:20 成年"
	
	 * 路徑: /api/old?name=Ann&age=16
	 * 結果: "姓名:Ann 年齡:16 未成年"
	 * */
	@GetMapping("/old")
	public String old(@RequestParam String name, @RequestParam Integer age) {
		String result = age >= 18 ? "成年" : "未成年";
		return "姓名:%s 年齡:%d %s".formatted(name, age, result);
	}
	
	/** 
	 * 4. Lab 練習 I
	 * 路徑: /api/bmi?h=170&w=60
	 * 判斷: bmi <= 18 顯示過輕, bmi > 23 顯示過重
	 * 執行結果: 身高:170cm 體重:60kg bmi=20.76(正常)
	*/
	@GetMapping("/bmi")
	public String bmi(@RequestParam Double h, @RequestParam Double w) {
		Double bmiValue = w / Math.pow(h/100, 2);
		String result = (bmiValue <= 18) ? "過輕" : (bmiValue > 23) ? "過重" : "正常";
		return "身高:%.1fcm 體重:%.1fkg bmi=%.2f(%s)".formatted(h, w, bmiValue, result);
	}
	
	
}
