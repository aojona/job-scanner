package ru.kirill.webui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import ru.kirill.webui.config.CookieProperties;
import ru.kirill.webui.config.PageRequestProperties;

@EnableDiscoveryClient
@SpringBootApplication
@EnableConfigurationProperties({CookieProperties.class, PageRequestProperties.class})
public class WebUiApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebUiApplication.class, args);
	}

}
