package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@EnableEurekaClient
@EnableRetry
public class OneplatformSampleGiftcardconsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(OneplatformSampleGiftcardconsumerApplication.class, args);
	}
}
