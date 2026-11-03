package com.example.demo.stock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.stock.entity.MyStock;

@Repository
public interface MyStockRepository extends JpaRepository<MyStock, Integer> {

}
