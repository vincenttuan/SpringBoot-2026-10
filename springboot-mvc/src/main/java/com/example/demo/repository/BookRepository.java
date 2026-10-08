package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.Book;

// Repository 相當於 DAO
// 定義相關方法
public interface BookRepository {
	
	// 查找所有書籍
	List<Book> findAllBooks();
	
	// 查找指定書籍
	Optional<Book> getBookById(Integer id);
	
	// 新增書籍
	Boolean addBook(Book book);
	
	// 修改書籍
	Boolean updateBook(Integer id, Book book);
	
	// 刪除書籍
	Boolean deleteBookById(Integer id);
	
}
