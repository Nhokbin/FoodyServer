package com.anhvu.foody;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages={"com.anhvu.foody"})
public class FoodyServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodyServerApplication.class, args);
	}
}
