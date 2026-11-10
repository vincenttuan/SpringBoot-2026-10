package com.example.demo.stock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.stock.repository.MyStockRepository;

@Service
public class MyStockService {
	
	@Autowired
	private MyStockRepository myStockRepository;
	
	
	
}
