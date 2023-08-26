package ru.kirill.restapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import ru.kirill.commondto.request.JwtRequest;
import ru.kirill.commondto.response.JwtResponse;
import ru.kirill.restapi.security.SecurityUser;
import ru.kirill.restapi.util.JwtTokenProvider;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberService memberService;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    public JwtResponse login(JwtRequest jwtRequest) {
        String username = jwtRequest.getUsername();
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, jwtRequest.getPassword()));
        SecurityUser securityUser = memberService.loadUserByUsername(username);
        String accessToken = jwtTokenProvider.generateAccessToken(securityUser);
        return new JwtResponse(username, accessToken);
    }
}
