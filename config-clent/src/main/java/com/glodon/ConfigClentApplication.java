package com.glodon;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableEurekaClient
public class ConfigClentApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(ConfigClentApplication.class, args);
	}

	@Value("${foo}")
	String foo;	
	
	@RequestMapping("/findFoo")
	public String findFoo() {
		return foo;
	}
}
