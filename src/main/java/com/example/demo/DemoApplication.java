package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//扫描mybatis mapper包路径
@MapperScan(basePackages = "com.example.demo.mapper")
//扫描所有的需要的包，包含一些自用的工具类包所在的路径
@ComponentScan(basePackages = {"com.example.demo","org.n3r.idworker"})
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
