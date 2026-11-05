package com.example.demo.stock.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.stock.entity.MyStock;

@Repository
public interface MyStockRepository extends JpaRepository<MyStock, Integer> {
	
	// 利用 TSQL + symbol 來查詢資料
	// value: select * from my_stock where symbol = :symbol;
	// nativeQuery = true 表示 value 是 TSQL 的語法
	@Query(value = "select * from my_stock where symbol = :symbol", nativeQuery = true)
	Optional<MyStock> findBySymbol_useTSQL(@Param("symbol") String symbol);
	
}
