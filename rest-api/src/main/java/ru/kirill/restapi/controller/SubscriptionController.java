package ru.kirill.restapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kirill.commondto.request.SubscriptionRequest;
import ru.kirill.commondto.response.PageResponse;
import ru.kirill.commondto.response.SubscriptionResponse;
import ru.kirill.restapi.service.SubscriptionService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/subscription")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @GetMapping
    private ResponseEntity<PageResponse<SubscriptionResponse>> getAll(Pageable pageable) {
        PageResponse<SubscriptionResponse> pageResponse = PageResponse.of(subscriptionService.getAll(pageable));
        return new ResponseEntity<>(pageResponse, HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<SubscriptionResponse> create(@RequestBody SubscriptionRequest subscriptionRequest) {
        SubscriptionResponse subscriptionResponse = subscriptionService.create(subscriptionRequest);
        return new ResponseEntity<>(subscriptionResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    private ResponseEntity<SubscriptionResponse> get(@PathVariable long id) {
        SubscriptionResponse subscriptionResponse = subscriptionService.get(id);
        return new ResponseEntity<>(subscriptionResponse, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    private ResponseEntity<SubscriptionResponse> update(@PathVariable long id,
                                                  @RequestBody SubscriptionRequest subscriptionRequest) {
        SubscriptionResponse subscriptionResponse = subscriptionService.update(id, subscriptionRequest);
        return new ResponseEntity<>(subscriptionResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<?> delete(@PathVariable long id) {
        subscriptionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
