package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Book;

@Repository // 專門負責 "資料存取" 的元件, Spring 但看到此註解會自動建立該物件並進行管理
public class BookRepositoryJdbc implements BookRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate; // 自動綁定 spring data 的 JdbcTemplate 物件
	
	@Override
	public List<Book> findAllBooks() {
		String sql = "select id, name, price, amount, pub from book";
		// 利用 BeanPropertyRowMapper<>(Book.class) 會自動將資料表中的每一筆紀錄注入到 Book 物件中
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Book.class));
	}

	@Override
	public Optional<Book> getBookById(Integer id) {
		String sql = "select id, name, price, amount, pub from book where id=?";
		// 單筆查詢時若沒查到資料會拋出 EmptyResultDataAccessException
		try {
			Book book = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Book.class), id);
			return Optional.of(book);
		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}

	@Override
	public Boolean addBook(Book book) {
		String sql = "insert into book(name, price, amount, pub) values(?, ?, ?, ?)";
		int rows = jdbcTemplate.update(sql, book.getName(), book.getPrice(), book.getAmount(), book.getPub());
		return rows > 0;
	}

	@Override
	public Boolean updateBook(Integer id, Book book) {
		String sql = "update book set name=?, price=?, amount=?, pub=? where id=?";
		int rows = jdbcTemplate.update(sql, book.getName(), book.getPrice(), book.getAmount(), book.getPub(), book.getId());
		return rows > 0;
	}

	@Override
	public Boolean deleteBookById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
