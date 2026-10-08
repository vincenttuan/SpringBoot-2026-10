package com.example.demo.service;

import java.util.List;

import com.example.demo.exception.BookException;
import com.example.demo.model.Book;

// 定義書籍服務規格
public interface BookService {
	
	List<Book> findAllBooks();
	Book getBookById(Integer id) throws BookException;
	
	void addBook(Book book) throws BookException;
	void updateBook(Integer id, Book book) throws BookException;
	void updateBookName(Integer id, String bookName) throws BookException;
	void updateBookPrice(Integer id, Double bookPrice) throws BookException;
	void updateBookNameAndPrice(Integer id, String bookName, Double bookPrice) throws BookException;
	
	void deleteBookById(Integer id) throws BookException;
	
}
