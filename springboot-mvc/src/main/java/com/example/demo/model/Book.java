package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
-- 建立 book 資料表
create table if not exists book(
	`id` int auto_increment,
    `name` varchar(50) unique not null,
    `price` decimal(10, 1) not null,
    `amount` int not null,
    `pub` boolean not null default 0,
    primary key (`id`)
)
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
	private Integer id;
	private String name;
	private Double price;
	private Integer amount;
	private Boolean pub;
}
