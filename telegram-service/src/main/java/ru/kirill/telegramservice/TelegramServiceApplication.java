package ru.kirill.telegramservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class TelegramServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TelegramServiceApplication.class, args);
    }

}
