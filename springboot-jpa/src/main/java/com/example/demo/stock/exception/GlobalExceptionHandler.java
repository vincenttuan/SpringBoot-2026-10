package com.example.demo.stock.exception;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.stock.response.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<ApiResponse<Void>> handleNotFound(NoSuchElementException e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.error(e.getMessage()));
	}
	
	// 上傳資料錯誤
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiResponse<Void>> handleNotValid(MethodArgumentNotValidException e) {
		// 收集所有的 defaultMessage
		List<String> errorMessages = e.getBindingResult()
				.getFieldErrors()
				.stream()
				.map(FieldError::getDefaultMessage)
				.toList();
		
		return errorMessages.size() == 0 ? 
				ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.error(e.getMessage()))
				:
				ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.error(errorMessages.toString()));
	}
	
}
