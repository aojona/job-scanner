package ru.kirill.vacancynotifierservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class VacancyNotifierServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(VacancyNotifierServiceApplication.class, args);
	}
}
