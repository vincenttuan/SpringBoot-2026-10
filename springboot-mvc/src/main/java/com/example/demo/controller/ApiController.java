package com.example.demo.controller;

import java.util.IntSummaryStatistics;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.BMI;
import com.example.demo.response.ApiResponse;

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
	
	/**
	 * 5. 同名多筆
	 * 路徑: /api/ages?age=17&age=21&age=20
	 * 印出所有年齡與平均年齡
	 * */
	@GetMapping("/ages")
	public String ages(@RequestParam(name = "age") List<Integer> ages) {
		
		double avg = ages.stream() // Stream<Integer>
						 //.mapToInt(age -> Integer.valueOf(age)) // Integer 轉 int -> IntStream
						 .mapToInt(Integer::valueOf) // Integer 轉 int -> IntStream
						 .average()
						 .orElse(0);
		
		return "所有年齡:%s 平均年齡:%.1f".formatted(ages, avg);
	}
	
	/**
	 * 6. Lab 練習: 得到多筆 score 資料
	 * 路徑: "/api/scores?score=80&score=100&score=50&score=70&score=30"
	 * 印出分數與平均, 總分, 最高分, 最低分
	 * */
	@GetMapping("/scores")
	public String scores(@RequestParam(name = "score") List<Integer> scores) {
		/*
		double avg = scores.stream().mapToInt(Integer::valueOf).average().orElse(0);
		int    max = scores.stream().mapToInt(Integer::valueOf).max().orElse(0);
		int    min = scores.stream().mapToInt(Integer::valueOf).min().orElse(0);
		int    sum = scores.stream().mapToInt(Integer::valueOf).sum();
		
		return "所有分數:%s 平均:%.1f 總分:%d 最高分:%d 最低分:%d".formatted(scores, avg, sum, max, min);
		*/
		// 統計物件
		IntSummaryStatistics stat = scores.stream().mapToInt(Integer::valueOf).summaryStatistics();
		double avg = stat.getAverage();
		int    max = stat.getMax();
		int    min = stat.getMin();
		long   sum = stat.getSum();
		return "所有分數:%s 平均:%.1f 總分:%d 最高分:%d 最低分:%d".formatted(scores, avg, sum, max, min);
	}
	
	/**
	 * 7. 路徑參數
	 * 查詢學生資料
	 * 請求參數設計:
	 * 路徑: "/api/student?id=1"
	 * 路徑: "/api/student?id=2"
	 * 路徑: "/api/student?id=3"
	 * 
	 * 路徑參數設計:
	 * 路徑: "/api/student/1"
	 * 路徑: "/api/student/2"
	 * 路徑: "/api/student/3"
	 * 
	 * */
	@GetMapping("/student/{id}")
	public String student(@PathVariable Integer id) {
		return "取得 %d 號學生資料".formatted(id);
	}
	
	/**
	 * 8. 回傳 json 結構
	 * 路徑: /api/json/bmi1?h=170&w=60
	 * 結果:
	 
	  {
	  	"message": "BMI 執行結果",
	  	"data": {
	  		"height": 170.0,
	  		"weight": 60.0,
	  		"bmi": 20.76,
	  		"result": "正常"
	  	}
	  }
	  
	 * */
	@GetMapping(value = "/json/bmi1", produces = "application/json;charset=utf-8")
	public String bmi1(@RequestParam Double h, @RequestParam Double w) {
		double bmi = w / Math.pow(h/100, 2);
		String result = bmi <= 18 ? "過輕" : bmi > 23 ? "過重" : "正常";
		
		String json = """
					{
					  	"message": "BMI 執行結果",
					  	"data": {
					  		"height": %.1f,
					  		"weight": %.1f,
					  		"bmi": %.2f,
					  		"result": "%s"
					  	}
				  	}
				""".formatted(h, w, bmi, result);
		
		return json.trim();
	}
	
	@GetMapping(value = "/json/bmi2", produces = "application/json;charset=utf-8")
	public BMI bmi2(@RequestParam Double h, @RequestParam Double w) {
		double bmiValue = w / Math.pow(h/100, 2);
		String result = bmiValue <= 18 ? "過輕" : bmiValue > 23 ? "過重" : "正常";
		
		BMI bmi = new BMI(h, w, bmiValue, result);
		
		return bmi;
	}
	
	@GetMapping(value = "/json/bmi3", produces = "application/json;charset=utf-8")
	public ApiResponse<BMI> bmi3(@RequestParam Double h, @RequestParam Double w) {
		// 檢查資料
		if(h <= 0 || w <= 0) {
			return ApiResponse.error("身高體重輸入有誤");
		}
		
		double bmiValue = w / Math.pow(h/100, 2);
		String result = bmiValue <= 18 ? "過輕" : bmiValue > 23 ? "過重" : "正常";
		
		BMI bmi = new BMI(h, w, bmiValue, result);
		
		return ApiResponse.success("BMI 執行結果", bmi);
	}
	
	@GetMapping(value = "/json/bmi4", produces = "application/json;charset=utf-8")
	public ResponseEntity<ApiResponse<BMI>> bmi4(@RequestParam Double h, @RequestParam Double w) {
		// 檢查資料
		if(h <= 0 || w <= 0) {
			//return ApiResponse.error("身高體重輸入有誤");
			return ResponseEntity.badRequest().body(ApiResponse.error("身高體重輸入有誤"));
		}
		
		double bmiValue = w / Math.pow(h/100, 2);
		String result = bmiValue <= 18 ? "過輕" : bmiValue > 23 ? "過重" : "正常";
		
		BMI bmi = new BMI(h, w, bmiValue, result);
		
		//return ApiResponse.success("BMI 執行結果", bmi);
		return ResponseEntity.ok(ApiResponse.success("BMI 執行結果", bmi));
	}
	
	// 路徑: /api/json/bmi5?height=170&weight=60
	@GetMapping(value = "/json/bmi5", produces = "application/json;charset=utf-8")
	public ResponseEntity<ApiResponse<BMI>> bmi5(BMI bmi) {
		// 檢查資料
		if(bmi.getHeight() <= 0 || bmi.getWeight() <= 0) {
			//return ApiResponse.error("身高體重輸入有誤");
			return ResponseEntity.badRequest().body(ApiResponse.error("身高體重輸入有誤"));
		}
		
		double bmiValue = bmi.getWeight() / Math.pow(bmi.getHeight()/100, 2);
		String result = bmiValue <= 18 ? "過輕" : bmiValue > 23 ? "過重" : "正常";
		
		bmi.setBmi(bmiValue);
		bmi.setResult(result);
		
		//return ApiResponse.success("BMI 執行結果", bmi);
		return ResponseEntity.ok(ApiResponse.success("BMI 執行結果", bmi));
	}
	
	
	
}





