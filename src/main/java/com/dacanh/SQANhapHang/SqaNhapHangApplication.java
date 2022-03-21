package com.dacanh.SQANhapHang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class SqaNhapHangApplication {

	public static void main(String[] args) {
		SpringApplication.run(SqaNhapHangApplication.class, args);
	}

}
