package com.smartcloud.iot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableDiscoveryClient
public class IoDevicesServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(IoDevicesServiceApplication.class, args);
	}

	@RequestMapping(value = "/")
	public String check() {
		return "check";
	}



}
