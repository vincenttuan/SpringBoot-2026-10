package com.example.demo.exception;

// 書籍錯誤處理
public class BookException extends Exception {
	
	public BookException(String errorMessage) {
		super(errorMessage);
		System.err.printf("[BookException]: %s%n", errorMessage); // 將錯誤訊息印出
	}
}
