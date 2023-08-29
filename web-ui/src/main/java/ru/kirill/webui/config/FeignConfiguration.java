package ru.kirill.webui.config;

import feign.RequestInterceptor;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.kirill.webui.util.HttpUtil;

@Configuration
@EnableFeignClients(basePackages = "ru.kirill.webui.feign")
public class FeignConfiguration {

    private static final String COOKIE_HEADER = "Cookie";

    @Bean
    public RequestInterceptor requestTokenInterceptor() {
        return requestTemplate -> requestTemplate.header(COOKIE_HEADER, HttpUtil.getHeader(COOKIE_HEADER));
    }
}
