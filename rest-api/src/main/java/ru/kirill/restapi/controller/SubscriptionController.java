package ru.kirill.restapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.kirill.commondto.request.PageableRequest;
import ru.kirill.commondto.request.SubscriptionRequest;
import ru.kirill.commondto.response.PageResponse;
import ru.kirill.commondto.response.SubscriptionResponse;
import ru.kirill.restapi.service.SubscriptionService;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/subscription")
@SecurityRequirement(name = "basic")
@Tag(name="Упраление подписками")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @Operation(summary = "Показать страницу с подписками")
    public ResponseEntity<PageResponse<SubscriptionResponse>> getAll(PageableRequest pageable) {
        PageResponse<SubscriptionResponse> pageResponse = PageResponse.of(subscriptionService.getAll(pageable));
        return new ResponseEntity<>(pageResponse, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN') or authentication.principal.id == #subscriptionRequest.memberId")
    @Operation(summary = "Добавить подписку")
    public ResponseEntity<SubscriptionResponse> create(@RequestBody @Valid SubscriptionRequest subscriptionRequest) {
        SubscriptionResponse subscriptionResponse = subscriptionService.create(subscriptionRequest);
        return new ResponseEntity<>(subscriptionResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @PostAuthorize("hasAnyAuthority('ADMIN') or #returnObject.body.memberIds.contains(authentication.principal.id)")
    @Operation(summary = "Найти подписку")
    public ResponseEntity<SubscriptionResponse> get(@PathVariable long id) {
        SubscriptionResponse subscriptionResponse = subscriptionService.get(id);
        return new ResponseEntity<>(subscriptionResponse, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN') or authentication.principal.id == #subscriptionRequest.memberId")
    @Operation(summary = "Обновить подписку")
    public ResponseEntity<SubscriptionResponse> update(@PathVariable long id,
                                                  @RequestBody @Valid SubscriptionRequest subscriptionRequest) {
        SubscriptionResponse subscriptionResponse = subscriptionService.update(id, subscriptionRequest);
        return new ResponseEntity<>(subscriptionResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PostAuthorize("hasAnyAuthority('ADMIN') or authentication.principal.id == #returnObject.body.memberId")
    @Operation(summary = "Удалить подписку")
    public ResponseEntity<?> delete(@PathVariable long id) {
        subscriptionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
