package ru.kirill.webui.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.kirill.commondto.request.ChatRequest;
import ru.kirill.commondto.request.JwtRequest;
import ru.kirill.commondto.request.SubscriptionRequest;
import ru.kirill.commondto.response.*;

@FeignClient(name = "rest-api", path = "/api")
public interface RestApiClient {

    @PostMapping("/auth/login")
    ResponseEntity<JwtResponse> login(@RequestBody JwtRequest jwtRequest);

    @PostMapping("/auth/join")
    ResponseEntity<JwtResponse> join(@RequestBody JwtRequest jwtRequest);

    @GetMapping("/auth/member")
    ResponseEntity<MemberResponse> getMemberFromToken();

    @PostMapping("/member/addSubscription")
    ResponseEntity<?> addSubscription(@RequestBody SubscriptionRequest subscriptionRequest);

    @DeleteMapping("/member/removeSubscription")
    ResponseEntity<?> deleteSubscription(@RequestBody SubscriptionRequest subscriptionRequest);

    @PostMapping("/member/updateChatId")
    ResponseEntity<?> updateChatId(@RequestBody ChatRequest chatRequest);

    @GetMapping("/statistics")
    ResponseEntity<Content<AverageVacancyStatistics>> getAverageStatistics();
}