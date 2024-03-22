package com.example.cozastoreweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CozastorewebApplication {

	public static void main(String[] args) {
		SpringApplication.run(CozastorewebApplication.class, args);
	}

}
