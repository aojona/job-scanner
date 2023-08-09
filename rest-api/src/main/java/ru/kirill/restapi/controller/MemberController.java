package ru.kirill.restapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kirill.commondto.request.MemberRequest;
import ru.kirill.commondto.response.MemberResponse;
import ru.kirill.commondto.response.PageResponse;
import ru.kirill.restapi.service.MemberService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @GetMapping
    public ResponseEntity<PageResponse<MemberResponse>> getAll(Pageable pageable) {
        PageResponse<MemberResponse> pageResponse = PageResponse.of(memberService.getAll(pageable));
        return new ResponseEntity<>(pageResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MemberResponse> create(@RequestBody MemberRequest memberRequest) {
        MemberResponse memberResponse = memberService.create(memberRequest);
        return new ResponseEntity<>(memberResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberResponse> get(@PathVariable long id) {
        MemberResponse memberResponse = memberService.get(id);
        return new ResponseEntity<>(memberResponse, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MemberResponse> update(@PathVariable long id,
                                                  @RequestBody MemberRequest memberRequest) {
        MemberResponse memberResponse = memberService.update(id, memberRequest);
        return new ResponseEntity<>(memberResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        memberService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
