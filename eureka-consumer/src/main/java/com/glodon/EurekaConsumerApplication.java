package com.glodon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableEurekaClient
@SpringBootApplication
@EnableCircuitBreaker //使用断路器
public class EurekaConsumerApplication {
	
	@Bean //定义REST客户端，RestTemplate实例  
    @LoadBalanced //开启负债均衡的能力  
    RestTemplate restTemplate() {  
        return new RestTemplate();  
    }  

	public static void main(String[] args) {
		SpringApplication.run(EurekaConsumerApplication.class, args);
	}
}
