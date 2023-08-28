package ru.kirill.restapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kirill.commondto.request.JwtRequest;
import ru.kirill.commondto.response.JwtResponse;
import ru.kirill.restapi.service.AuthService;
import ru.kirill.restapi.service.MemberService;
import ru.kirill.restapi.util.JwtUtil;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final MemberService memberService;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest authRequest) {
        JwtResponse token = authService.login(authRequest);
        return ResponseEntity
                .ok()
                .header(HttpHeaders.SET_COOKIE, token.getAccessToken())
                .body(token);
    }

    @PostMapping("/join")
    public ResponseEntity<JwtResponse> join(@RequestBody JwtRequest jwtRequest) {
        memberService.create(JwtUtil.mapToMemberRequest(jwtRequest));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
