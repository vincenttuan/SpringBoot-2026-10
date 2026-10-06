package com.example.demo.response;

import lombok.AllArgsConstructor;
import lombok.Data;

// 統一回傳格式
@Data
@AllArgsConstructor
public class ApiResponse<T> {
	
	private String message; // 訊息: 查詢成功, 新增成功, 新增失敗 etc...
	private T data; // payload 實際資料
	
	// 成功回應
	public static <T> ApiResponse<T> success(String message, T data) {
		return new ApiResponse<T>(message, data);
	}
	
	// 失敗回應
	public static <T> ApiResponse<T> error(String message) {
		return new ApiResponse<T>(message, null);
	}
	
}
