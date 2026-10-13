package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.BookException;
import com.example.demo.model.Book;
import com.example.demo.response.ApiResponse;
import com.example.demo.service.BookService;

/**
 * BookController
 * ============================================
 * 📘 功能說明：
 * 本類別是「書籍管理系統」的 RESTful API 控制器，
 * 負責接收前端的 HTTP 請求，
 * 並呼叫 Service 層進行商業邏輯處理
 * Repository 負責資料的 CRUD
 * 最後回傳 JSON 結果。
 * 
 * --------------------------------------------
 * 📌 使用技術：
 * - Spring Boot (REST API)
 * - ResponseEntity (控制 HTTP 狀態碼)
 * - ApiResponse (統一回傳格式)
 *
 * --------------------------------------------
 * 📌 RESTful API 設計概念：
 *
 * 方法        路徑            功能
 * --------------------------------------------
 * GET         /book          查詢全部書籍
 * GET         /book/{id}     查詢單一書籍
 * POST        /book          新增書籍
 * DELETE      /book/{id}     刪除書籍
 * PUT         /book/{id}     更新整本書 (完整更新)
 * PATCH       /book/{id}     部分更新 (name or price or both)
 *
 * --------------------------------------------
 * 📌 分層架構 (MVC)：
 *
 * Controller → 接收請求 (HTTP)
 * Service    → 商業邏輯
 * Repository → 資料庫操作
 *
 * --------------------------------------------
 * 📌 設計重點：
 *
 * 1️. 使用 ResponseEntity
 *   - 可控制 HTTP Status (200, 400, 404...)
 *
 * 2️. 使用 ApiResponse
 *   - 統一 JSON 回傳格式
 *   {
 *     "message": "...",
 *     "data": ...
 *   }
 *
 * 3️. 例外處理 (Exception Handling)
 *   - 捕捉 BookException
 *   - 避免系統直接 crash
 *
 * 4️. RESTful 設計
 *   - 使用 HTTP Method 表達行為
 *   - URL 表示資源 (book)
 *
 * --------------------------------------------
 * 📌 前端呼叫範例：
 *
 * GET    http://localhost:8080/book
 * POST   http://localhost:8080/book
 * PUT    http://localhost:8080/book/1
 * PATCH  http://localhost:8080/book/1
 * DELETE http://localhost:8080/book/1
 *
 * --------------------------------------------
 * 📌 跨域設定：
 * @CrossOrigin 允許前端 (localhost:5173) 呼叫 API
 * (常見於 React / Vite 開發環境)
 *
 * ============================================
 */

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/book")
public class BookController {
	
	@Autowired
	@Qualifier("bookServiceImpl")
	private BookService bookService;
	
	// 查詢所有書籍
	@GetMapping
	public ResponseEntity<ApiResponse<List<Book>>> findAllBooks() {
		
		List<Book> books = bookService.findAllBooks();
		if(books == null || books.size() == 0) {
			return ResponseEntity.badRequest().body(ApiResponse.error("查無任何書籍"));
		}
		return ResponseEntity.ok(ApiResponse.success("查詢成功, 筆數:%d".formatted(books.size()), books));
	}
	
	// 查詢指定書籍
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<Book>> getBook(@PathVariable Integer id) {
		
		try {
			Book book = bookService.getBookById(id);
			return ResponseEntity.ok(ApiResponse.success("查詢成功", book));
		} catch (BookException e) {
			return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
		}
		
	}
	
	/* 新增書籍
	 * @RequestBody 的內容
	  {
	  	"name": "Java",
    	"price": 13.5,
    	"amount": 15,
    	"pub": true
	  }
	 */
	@PostMapping
	public ResponseEntity<ApiResponse<Book>> addBook(@RequestBody Book book) {
		
		try {
			bookService.addBook(book);
			return ResponseEntity.ok(ApiResponse.success("新增成功", book));
		} catch (BookException e) {
			return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
		}
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<Book>> updateBook(@PathVariable Integer id, @RequestBody Book book) {
		
		try {
			bookService.updateBook(id, book);
			return ResponseEntity.ok(ApiResponse.success("修改成功", book));
		} catch (BookException e) {
			return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
		}
		
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<ApiResponse<Book>> patchBook(@PathVariable Integer id, @RequestBody Book book) {
		// 1. 判斷 name 或 price 的有效資料
		boolean hasName  = book.getName()  != null && !book.getName().isEmpty() && !book.getName().isBlank();
		boolean hasPrice = book.getPrice() != null && !book.getPrice().isNaN()  && book.getPrice() > 0;
		
		if(!hasName && !hasPrice) {
			return ResponseEntity.badRequest().body(ApiResponse.error("修改失敗! 請提供要修改的名稱或價格"));
		}
		
		// 2. 進行修改
		try {
			if(hasName && hasPrice) bookService.updateBookNameAndPrice(id, book.getName(), book.getPrice());
			if(hasName && !hasPrice) bookService.updateBookName(id, book.getName());
			if(!hasName && hasPrice) bookService.updateBookPrice(id, book.getPrice());
			
			// 取得修改後的書籍
			book = bookService.getBookById(id);
			return ResponseEntity.ok(ApiResponse.success("修改成功", book));
		} catch (BookException e) {
			return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
		}
		
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<String>> deleteBook(@PathVariable Integer id) {
		
		try {
			bookService.deleteBookById(id);
			return ResponseEntity.ok(ApiResponse.success("刪除成功", null));
		} catch (BookException e) {
			return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
		}
		
	}
	
	
}




