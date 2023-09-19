package ru.kirill.restapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import ru.kirill.restapi.config.SearchProperties;

@EnableDiscoveryClient
@SpringBootApplication
@EnableConfigurationProperties(SearchProperties.class)
public class RestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestApiApplication.class, args);
	}

}
