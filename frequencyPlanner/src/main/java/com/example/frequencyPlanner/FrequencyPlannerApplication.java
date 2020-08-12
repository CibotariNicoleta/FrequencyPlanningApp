package com.example.frequencyPlanner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import org.springframework.context.annotation.ComponentScan;



@ComponentScan(basePackages = { "com.example.frequencyPlanner." })
@EntityScan("com.example.frequencyPlanner.")

@SpringBootApplication()
public class FrequencyPlannerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FrequencyPlannerApplication.class, args);
	}
	
}
	
	
