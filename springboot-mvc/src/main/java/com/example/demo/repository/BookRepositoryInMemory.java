package com.example.demo.repository;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Repository;

import com.example.demo.model.Book;

@Repository // 專門負責 "資料存取" 的元件, Spring 但看到此註解會自動建立該物件並進行管理
public class BookRepositoryInMemory implements BookRepository {
	
	// InMemory 版 (透過一個 Java 集合物件來表示)
	private static List<Book> books = new CopyOnWriteArrayList<>();
	
	// static 初始資料區
	static {
		books.add(new Book(1, "小叮噹", 12.5, 20, true));
		books.add(new Book(2, "老夫子", 10.5, 30, true));
		books.add(new Book(3, "好小子", 13.5, 40, true));
		books.add(new Book(4, "小甜甜", 14.5, 10, false));
	}

	@Override
	public List<Book> findAllBooks() {
		return books;
	}

	@Override
	public Optional<Book> getBookById(Integer id) {
		return books.stream()
					.filter(book -> book.getId().equals(id))
					.findFirst();
	}

	@Override
	public Boolean addBook(Book book) {
		// 先找到書籍中目前最大 id 值
		//OptionalInt optMaxId = books.stream().mapToInt(bk -> bk.getId()).max(); 
		OptionalInt optMaxId = books.stream().mapToInt(Book::getId).max();
		
		// 建立 newId = 目前書庫中 id 的最大值 + 1
		Integer newId = optMaxId.isEmpty() ? 1 : optMaxId.getAsInt() + 1;
		
		// 將 newId 設定給 book
		book.setId(newId);
		
		// 新增書籍
		return books.add(book);
	}

	@Override
	public Boolean updateBook(Integer id, Book book) {
		// 根據 id 找到要修改的 book
		Optional<Book> optBook = getBookById(id);
		if(optBook.isEmpty()) {
			return false;
		}
		
		// 取得要修改的 book (原始資料)
		Book originalBook = optBook.get();
		
		// 逐筆更新欄位
		// Java 8 以前的寫法
		/*
		if(book.getName() != null) {
			originalBook.setName(book.getName());
		}
		
		if(book.getAmount() != null) {
			originalBook.setAmount(book.getAmount());
		}
		
		if(book.getPrice() != null) {
			originalBook.setPrice(book.getPrice());
		}
		
		if(book.getPub() != null) {
			originalBook.setPub(book.getPub());
		}
		*/
		// Java 8 以後的寫法
		Optional.ofNullable(book.getName()).ifPresent(originalBook::setName);
		Optional.ofNullable(book.getAmount()).ifPresent(originalBook::setAmount);
		Optional.ofNullable(book.getPrice()).ifPresent(originalBook::setPrice);
		Optional.ofNullable(book.getPub()).ifPresent(originalBook::setPub);
		
		return true;
	}

	@Override
	public Boolean deleteBookById(Integer id) {
		// 根據 id 找到要刪除的 book
		Optional<Book> optBook = getBookById(id);
		if(optBook.isEmpty()) {
			return false;
		}
		
		// 得到要刪除的原始 book
		Book originalBook = optBook.get();
		
		// 移除書籍
		return books.remove(originalBook);
	}
	
	
	
}
