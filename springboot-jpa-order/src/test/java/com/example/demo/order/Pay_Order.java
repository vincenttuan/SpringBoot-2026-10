package com.example.demo.order;

import java.nio.channels.IllegalSelectorException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Invoice;
import com.example.demo.model.Order;
import com.example.demo.model.OrderStatus;
import com.example.demo.repository.InvoiceRepository;
import com.example.demo.repository.OrderRepository;

@SpringBootTest
public class Pay_Order {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private InvoiceRepository invoiceRepository;
	
	@Test
	@Transactional
	@Commit
	public void pay() {
		Integer orderId = 1;
		String invoiceNo = "GH00000001";
		
		// 找到訂單
		Order order = orderRepository.findByIdWithItems(orderId).orElseThrow(() -> new RuntimeException("查無訂單"));
		
		// 確認訂單狀態
		// 只有待付款訂單可以結帳
		if(order.getStatus() != OrderStatus.PENDING_PAYMENT) {
			throw new IllegalStateException("只有待付款訂單可以結帳");
		}
		
		// 沒有商品細目不可結帳
		if(order.getItems().isEmpty()) {
			throw new IllegalStateException("沒有商品細目不可結帳");
		}
		
		// 同一張訂單不能重複開立發票
		if(order.getInvoice() != null) {
			throw new IllegalStateException("這張訂單已有發票");
		}
		
		// 同一張訂單不能重複開立發票
		if(invoiceRepository.existsById(invoiceNo)) {
			throw new IllegalStateException("此發票號碼: %s 已使用 請換一個號碼".formatted(invoiceNo));
		}
		
		// 變更訂單狀態 => 已付款
		order.setStatus(OrderStatus.PAID);
		
		// 建立發票
		Invoice invoice = new Invoice();
		invoice.setInvoiceNo(invoiceNo);
		invoice.setOrder(order);
		
		// 設定關聯
		// 明確儲存發票檔
		Invoice savedInvoice = invoiceRepository.save(invoice);
		
		// 同步給 order 做反向關聯
		order.setInvoice(savedInvoice);
		
		System.out.println("訂單編號: " + order.getId());
		System.out.println("訂單狀態: " + order.getStatus());
		System.out.println("發票號碼: " + savedInvoice.getInvoiceNo());
		
	}
	
}
