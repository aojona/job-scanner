package ru.kirill.restapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.kirill.commondto.request.MemberRequest;
import ru.kirill.commondto.request.PageableRequest;
import ru.kirill.commondto.response.MemberResponse;
import ru.kirill.commondto.response.PageResponse;
import ru.kirill.restapi.service.MemberService;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
@SecurityRequirement(name = "basic")
@Tag(name="Упраление пользователями")
public class MemberController {

    private final MemberService memberService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @Operation(summary = "Показать страницу с пользователями")
    public ResponseEntity<PageResponse<MemberResponse>> getAll(PageableRequest pageable) {
        PageResponse<MemberResponse> pageResponse = PageResponse.of(memberService.getAll(pageable));
        return new ResponseEntity<>(pageResponse, HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Зарегистрировать нового пользователя")
    public ResponseEntity<MemberResponse> create(@RequestBody MemberRequest memberRequest) {
        MemberResponse memberResponse = memberService.create(memberRequest);
        return new ResponseEntity<>(memberResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN') or authentication.principal.id == #id")
    @Operation(summary = "Найти пользователя")
    public ResponseEntity<MemberResponse> get(@PathVariable long id) {
        MemberResponse memberResponse = memberService.get(id);
        return new ResponseEntity<>(memberResponse, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN') or authentication.principal.id == #id")
    @Operation(summary = "Обновить пользователя")
    public ResponseEntity<MemberResponse> update(@PathVariable long id,
                                                  @RequestBody MemberRequest memberRequest) {
        MemberResponse memberResponse = memberService.update(id, memberRequest);
        return new ResponseEntity<>(memberResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN') or authentication.principal.id == #id")
    @Operation(summary = "Удалить пользователя")
    public ResponseEntity<?> delete(@PathVariable long id) {
        memberService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
