package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/study")
public class StudyController {
	
	@GetMapping
	public String study() {
		
		// StringMVC 會自動尋找 src/main/resources/study.html
		return "study"; 
	}
	
}
