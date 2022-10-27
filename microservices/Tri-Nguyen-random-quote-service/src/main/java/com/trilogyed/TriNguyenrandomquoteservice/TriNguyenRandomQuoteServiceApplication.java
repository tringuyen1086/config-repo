package com.trilogyed.TriNguyenrandomquoteservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class TriNguyenRandomQuoteServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TriNguyenRandomQuoteServiceApplication.class, args);
	}

}
