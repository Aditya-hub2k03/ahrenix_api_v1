package com.yp.ahrenix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class AhrenixApplication {

	public static void main(String[] args) {
		SpringApplication.run(AhrenixApplication.class, args);
	}

}
