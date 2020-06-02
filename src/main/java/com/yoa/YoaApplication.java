package com.yoa;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.yoa.dao")
public class YoaApplication {

	public static void main(String[] args) {
		SpringApplication.run(YoaApplication.class, args);
	}



}
