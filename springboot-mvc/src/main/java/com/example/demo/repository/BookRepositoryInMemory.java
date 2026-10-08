package com.example.demo.repository;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Repository;

import com.example.demo.model.Book;

@Repository // 專門負責 "資料存取" 的元件, Spring 但看到此註解會自動建立該物件並進行管理
public class BookRepositoryInMemory {
	
	// InMemory 版 (透過一個 Java 集合物件來表示)
	private static List<Book> books = new CopyOnWriteArrayList<>();
	
	
	
}
