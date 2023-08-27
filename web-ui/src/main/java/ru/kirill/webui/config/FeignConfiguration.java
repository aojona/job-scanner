package ru.kirill.webui.config;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.kirill.webui.util.RequestUtil;

@Configuration
public class FeignConfiguration {

    private static final String COOKIE_HEADER = "Cookie";

    @Bean
    public RequestInterceptor requestTokenInterceptor() {
        return requestTemplate -> requestTemplate.header(COOKIE_HEADER, RequestUtil.getHeader(COOKIE_HEADER));
    }
}
