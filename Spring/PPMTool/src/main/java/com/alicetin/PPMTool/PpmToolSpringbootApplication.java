package com.alicetin.PPMTool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = {"com.alicetin.PPMTool"})
public class PpmToolSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(PpmToolSpringbootApplication.class, args);
	}

}
