package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, String> {
	
	// 主鍵是 String 發票號碼；也可以沿著 invoice.order.id 查某張訂單的發票。
	Optional<Invoice> findByOrder_Id(Integer orderId);
}
