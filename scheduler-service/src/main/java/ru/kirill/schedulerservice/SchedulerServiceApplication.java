package ru.kirill.schedulerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SchedulerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchedulerServiceApplication.class, args);
	}

}
