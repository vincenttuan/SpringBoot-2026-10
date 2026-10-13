package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.demo.model.Book;

@Repository // 專門負責 "資料存取" 的元件, Spring 但看到此註解會自動建立該物件並進行管理
public class BookRepositoryJdbc implements BookRepository {

	@Override
	public List<Book> findAllBooks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Book> getBookById(Integer id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Boolean addBook(Book book) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean updateBook(Integer id, Book book) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean deleteBookById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
