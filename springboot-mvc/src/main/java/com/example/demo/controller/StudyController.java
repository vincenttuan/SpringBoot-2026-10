package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/study")
public class StudyController {
	
	@GetMapping
	public String study(Model model) {
		/*
		 * Model 的作用: 將資料傳遞給 Thymeleaf
		 * */
		
		// 傳送資料
		model.addAttribute("title", "Thymeleaf 學習中心");
		model.addAttribute("teacherName", "段維瀚老師");
		
		// StringMVC 會自動尋找 src/main/resources/templates/study.html
		return "study"; 
	}
	
}
