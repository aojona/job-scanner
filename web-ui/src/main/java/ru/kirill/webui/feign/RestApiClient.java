package ru.kirill.webui.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.kirill.commondto.request.JwtRequest;
import ru.kirill.commondto.request.SubscriptionRequest;
import ru.kirill.commondto.response.JwtResponse;
import ru.kirill.commondto.response.MemberResponse;

@FeignClient(name = "rest-api", path = "/api")
public interface RestApiClient {

    @PostMapping("/auth/login")
    ResponseEntity<JwtResponse> login(@RequestBody JwtRequest jwtRequest);

    @PostMapping("/auth/join")
    ResponseEntity<JwtResponse> join(@RequestBody JwtRequest jwtRequest);

    @GetMapping("/auth/member")
    ResponseEntity<MemberResponse> getMemberFromToken();

    @DeleteMapping("/subscription/delete")
    ResponseEntity<?> deleteSubscription(@RequestBody SubscriptionRequest subscription);
}