package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@EnableAutoConfiguration

public class JsondbVanillaAsSpringStarterDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(JsondbVanillaAsSpringStarterDemoApplication.class, args);
	}
}

