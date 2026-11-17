package com.example.demo.stock.controller;

import java.awt.print.Pageable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.stock.entity.MyStock;
import com.example.demo.stock.response.ApiResponse;
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
	@GetMapping
	public ResponseEntity<ApiResponse<Page<MyStock>>> findPage(
			@RequestParam(required = false, defaultValue = "1") int page,
			@RequestParam(required = false, defaultValue = "10") int size,
			@RequestParam(required = false, defaultValue = "id") String sort,
			@RequestParam(required = false, defaultValue = "asc") String dir) {
		
		Sort.Direction direction = dir.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
		Sort myStockSort = Sort.by(direction, sort);
		
		Page<MyStock> myStockPage = myStockService.findPage(page, size, myStockSort);
		
		return ResponseEntity.ok(ApiResponse.success("查詢成功", myStockPage));
	}
	
	
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
