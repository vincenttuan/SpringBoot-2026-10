package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.Study;

@Controller
@RequestMapping("/study")
public class StudyController {
	
	private static List<Study> studies = new ArrayList<>();
	
	static {
		studies.add(new Study(1, "Java 基礎", 6));
		studies.add(new Study(2, "Java Web", 16));
		studies.add(new Study(3, "Springboot", 26));
	}
	
//	@GetMapping("/a")
//	@ResponseBody
//	public String aaa() {
//		return "a";
//	}
//	
//	@GetMapping("/b")
//	public String bbb() {
//		return "b";
//	}
	
	@GetMapping
	public String study(Model model) {
		/*
		 * Model 的作用: 將資料傳遞給 Thymeleaf
		 * */
		
		// 傳送資料
		model.addAttribute("title", "Thymeleaf 學習中心");
		model.addAttribute("teacherName", "段維瀚老師");
		model.addAttribute("message", "今天開課");
		
		//model.addAttribute("score", 80); // 頁面要顯示: 80 分 及格
		//model.addAttribute("score", 40); // 頁面要顯示: 40 分 不及格
		model.addAttribute("score", new Random().nextInt(101));
		
		model.addAttribute("studies", studies);
		
		// SpringMVC 會自動尋找 src/main/resources/templates/study.html
		return "study"; 
	}
	
	@PostMapping("/add")
	public String add(Study study, Model model, RedirectAttributes redirectAttributes) {
		// 檢查 name 是否是空的
		if(study.getName().isBlank()) {
			// 回應訊息
			//model.addAttribute("message", "書籍名稱不可為空");
			// 回應網頁
			//return "ok";
			
			// 設定重導參數
			redirectAttributes.addFlashAttribute("errorMessage", "書籍名稱不可為空");
			return "redirect:/study";
		}
		
		// 設定 id
		int newId = studies.stream().mapToInt(Study::getId).max().orElse(0) + 1;
		study.setId(newId);
		// 新增資料
		studies.add(study);
		// 回應訊息
		//model.addAttribute("message", "新增成功");
		// 回應網頁
		//return "ok";
		redirectAttributes.addFlashAttribute("errorMessage", "新增成功");
		return "redirect:/study";
	}
	
	@PostMapping("/delete")
	public String delete(Integer id, RedirectAttributes redirectAttributes) {
		
		// 根據 id 尋找 study 物件
		Optional<Study> optStudy = studies.stream().filter(study -> study.getId().equals(id)).findFirst();
		
		// 判斷是否有找到資料 ?
		if(optStudy.isEmpty()) {
			redirectAttributes.addFlashAttribute("errorMessage", "書籍刪除失敗");
			return "redirect:/study";
		}
		
		// 取得 Study 物件
		Study study = optStudy.get();
		studies.remove(study);
		redirectAttributes.addFlashAttribute("errorMessage", "書籍刪除成功");
		return "redirect:/study";
		
	}
	
	@PostMapping("/edit")
	public String edit(Integer id, RedirectAttributes redirectAttributes, Model model) {
		
		// 根據 id 尋找 study 物件
		Optional<Study> optStudy = studies.stream().filter(study -> study.getId().equals(id)).findFirst();
		
		// 判斷是否有找到資料 ?
		if(optStudy.isEmpty()) {
			redirectAttributes.addFlashAttribute("errorMessage", "書籍編輯失敗");
			return "redirect:/study";
		}
		
		// 取得 Study 物件
		Study study = optStudy.get();
		model.addAttribute("study", study);
		return "edit";
		
	}
	
	@PostMapping("/update")
	public String update(Study uptStudy, RedirectAttributes redirectAttributes, Model model) {
		
		// 檢查 name 是否是空的
		if(uptStudy.getName().isBlank()) {
			// 設定重導參數
			model.addAttribute("errorMessage", "書籍名稱不可為空");
			model.addAttribute("study", uptStudy);
			return "edit";
		}
				
		// 根據 id 尋找 study 物件
		Integer id = uptStudy.getId();
		Optional<Study> optStudy = studies.stream().filter(study -> study.getId().equals(id)).findFirst();
		
		// 判斷是否有找到資料 ?
		if(optStudy.isEmpty()) {
			model.addAttribute("errorMessage", "查無此書修改書籍失敗");
			return "edit";
		}
		
		// 取得 Study 物件
		Study study = optStudy.get();
		// 進行修改
		study.setName(uptStudy.getName());
		study.setHours(uptStudy.getHours());
		
		model.addAttribute("message", "書籍修改成功");
		return "ok";
		
	}
	
}
