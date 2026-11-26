package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, String> {
	
	Optional<Invoice> findByOrder_Id(Integer orderId);
}
