package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.exception.BookException;
import com.example.demo.model.Book;
import com.example.demo.service.BookService;

@Controller
@RequestMapping("/ssr/book")
public class SSRBookController {
	
	@Autowired
	private BookService bookService;
	
	// 查詢所有書籍
	@GetMapping
	public String findAllBooks(Model model) {
		// 透過 bookService 查詢所有書籍
		List<Book> books = bookService.findAllBooks();
		// 將 books 放入到 model, model 會自動傳給 thymeleaf html 進行渲染
		model.addAttribute("books", books);
		// 指向 src\main\resources\templates\book\list.html
		return "book/list";
	}
	
	// 新增書籍
	@PostMapping
	public String addBook(Book book) {
		// 檢查 pub
		if(book.getPub() == null) {
			book.setPub(false);
		}
		
		try {
			bookService.addBook(book);
		} catch (BookException e) {
			e.printStackTrace();
		}
		
		// 新增完畢後重導到 /ssr/book
		return "redirect:/ssr/book";
	}
	
}
