package ru.kirill.restapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kirill.commondto.request.JwtRequest;
import ru.kirill.commondto.response.JwtResponse;
import ru.kirill.commondto.response.MemberResponse;
import ru.kirill.restapi.security.JwtTokenProvider;
import ru.kirill.restapi.service.AuthService;
import ru.kirill.restapi.service.MemberService;
import ru.kirill.restapi.util.JwtUtil;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@Tag(name="Authentication service")
public class AuthController {

    private final AuthService authService;
    private final MemberService memberService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    @Operation(summary = "Sign in")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest authRequest) {
        JwtResponse token = authService.login(authRequest);
        return ResponseEntity
                .ok()
                .header(HttpHeaders.SET_COOKIE, token.getAccessToken())
                .body(token);
    }

    @PostMapping("/join")
    @Operation(summary = "Sign up")
    public ResponseEntity<JwtResponse> join(@RequestBody @Valid JwtRequest jwtRequest) {
        memberService.create(JwtUtil.mapToMemberRequest(jwtRequest));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/member")
    @Operation(summary = "Get member from JWT token")
    public ResponseEntity<MemberResponse> getMemberFromToken(HttpServletRequest request) {
        String token = jwtTokenProvider.getAccessTokenFromCookies(request);
        return token != null
                ? new ResponseEntity<>(memberService.get(jwtTokenProvider.getUserId(token)), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.OK);
    }
}
