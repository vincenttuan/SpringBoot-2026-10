package com.example.demo;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

public class StudyBigDecimal {
	
	@Test
	public void test() {
		// 學習 BigDecimal
		
		System.out.println(0.1 + 0.1);
		System.out.println(0.1 + 0.1 == 0.2);
		
		System.out.println(0.1 + 0.1 + 0.1);
		System.out.println(0.1 + 0.1 + 0.1 == 0.3);
		
		BigDecimal a = BigDecimal.valueOf(0.1);
		BigDecimal b = BigDecimal.valueOf(0.1);
		BigDecimal c = BigDecimal.valueOf(0.1);
		
		System.out.println(a.add(b).add(c));
		System.out.println(a.add(b).add(c).doubleValue() == 0.3);
		System.out.println(a.add(b).add(c).equals(BigDecimal.valueOf(0.3)));
		
		
	}
	
}
