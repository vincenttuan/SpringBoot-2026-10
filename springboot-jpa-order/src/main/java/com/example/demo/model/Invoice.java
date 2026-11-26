package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * 發票檔 
 * 號碼範例: GH00000001
 * */
@Getter
@Setter
@Entity
@Table
public class Invoice {
	
	@Id
	@Column(name = "invoice_no", length = 10, nullable = false, updatable = false , comment = "發票號碼")
	private String invoiceNo;
	
	@OneToOne(optional = false) // 開了發票一定要有訂單 (發票檔裡的訂單欄位不可以是空的)
	@JoinColumn(name = "order_id", nullable = false, unique = true, updatable = false, comment = "訂單編號")
	private Order order;

	@Override
	public String toString() {
		return "Invoice [invoiceNo=" + invoiceNo + "]";
	}
	
}
