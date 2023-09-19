package ru.kirill.webui.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kirill.commondto.request.ChatRequest;
import ru.kirill.commondto.request.JwtRequest;
import ru.kirill.commondto.request.PageableRequest;
import ru.kirill.commondto.request.SubscriptionRequest;
import ru.kirill.commondto.response.*;

import java.util.List;

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
    ResponseEntity<?> removeSubscription(@RequestBody SubscriptionRequest subscriptionRequest);

    @PostMapping("/member/updateChatId")
    ResponseEntity<?> updateChatId(@RequestBody ChatRequest chatRequest);

    @GetMapping("/statistics/random")
    ResponseEntity<Content<AverageVacancyStatistics>> getAverageStatistics();

    @GetMapping("/statistics/member")
    ResponseEntity<ContentMap<String, List<StatisticsResponse>>> getMemberStatistics();

    @GetMapping("/subscription")
    ResponseEntity<PageResponse<SubscriptionResponse>> getAll(@SpringQueryMap PageableRequest pageable);
}