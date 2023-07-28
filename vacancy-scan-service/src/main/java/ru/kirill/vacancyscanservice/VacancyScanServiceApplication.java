package ru.kirill.vacancyscanservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class VacancyScanServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(VacancyScanServiceApplication.class, args);
    }

}
