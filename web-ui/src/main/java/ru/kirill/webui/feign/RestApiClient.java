package ru.kirill.webui.feign;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "rest-api", path = "/api")
public interface RestApiClient {

}