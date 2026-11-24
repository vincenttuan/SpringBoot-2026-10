package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
	
	// JPQL 使用 Java Entity 與屬性名稱；以下查詢同時載入訂單、明細及商品。
		// select distinct o：回傳訂單物件，同一張訂單不要重複出現在結果中。
		// from Order o：從 Order 類別查詢訂單，使用 o 作為訂單的別名。
		// left join fetch o.items item：載入訂單明細，取別名 item；沒有明細的訂單也保留。
		// left join fetch item.product：再載入明細的商品，之後讀取商品時不用再補查。
		
		/**
	 	JPQL:
		 	select distinct o
			from Order o
			left join fetch o.items item
			left join fetch item.product
		----------------------------------------  
	  	SQL:
		  	SELECT
			    o.id         AS order_id,
			    o.status     AS order_status,
			    i.id         AS item_id,
			    i.quantity,
			    i.unit_price,
			    p.id         AS product_id,
			    p.name       AS product_name,
			    p.price      AS product_price
			FROM `order` AS o
			LEFT JOIN order_item AS i
			    ON i.order_id = o.id
			LEFT JOIN product AS p
			    ON p.id = i.product_id;
		 * */
		@Query("""
			    select distinct o
			    from Order o
			    left join fetch o.items item
			    left join fetch item.product
			    """)
		List<Order> findAllWithItems();
}
