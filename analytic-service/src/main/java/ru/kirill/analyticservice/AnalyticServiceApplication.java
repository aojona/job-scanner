package ru.kirill.analyticservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class AnalyticServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnalyticServiceApplication.class, args);
    }

}
