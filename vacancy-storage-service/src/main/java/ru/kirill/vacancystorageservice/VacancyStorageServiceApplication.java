package ru.kirill.vacancystorageservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class VacancyStorageServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(VacancyStorageServiceApplication.class, args);
	}

}
