package com.example.demo.stock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.stock.service.MyStockService;

/**
 * 查詢全部股票
 * GET /rest/mystock?page=1&size=10&sort=id&dir=asc
 * 查詢單一股票
 * GET /rest/mystock/1
 * 新增股票
 * POST /rest/mystock
 * 修改股票
 * PUT /rest/mystock/1
 * 刪除股票
 * DELETE /rest/mystock/1
 * */

@RestController
@RequestMapping("/rest/mystock")
public class MyStockRestController {
	
	@Autowired
	private MyStockService myStockService;
	
	/**
	 * 查詢全部股票
	 * GET /rest/mystock?page=1&size=10&sort=id&dir=asc
	 */
	
	
	
	/**
	 * 查詢單一股票
	 * GET /rest/mystock/1
	 */
	
	/**
	 * 新增股票
	 * POST /rest/mystock
	 */
	
	/**
	 * 修改股票
	 * PUT /rest/mystock/1
	 */
	
	/**
	 * 刪除股票
	 * DELETE /rest/mystock/1
	 * */
	
	
}
