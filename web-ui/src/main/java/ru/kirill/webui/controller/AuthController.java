package ru.kirill.webui.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kirill.commondto.request.JwtRequest;
import ru.kirill.commondto.response.JwtResponse;
import ru.kirill.webui.feign.RestApiClient;
import ru.kirill.webui.util.HttpUtil;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private static final String COOKIE_HEADER = "Set-Cookie";
    private static final String USER = "user";

    private final RestApiClient restApiClient;

    @GetMapping("/join")
    public String joinView(Model model) {
        model.addAttribute(USER, new JwtRequest());
        return "join";
    }

    @PostMapping("/join")
    public String submitJoinView(@ModelAttribute(USER) JwtRequest jwtRequest) {
        restApiClient.join(jwtRequest);
        return "redirect:/auth/login";
    }

    @GetMapping("/login")
    public String loginView(Model model) {
        model.addAttribute(USER, new JwtRequest());
        return "login";
    }

    @PostMapping("/login")
    public String submitLoginForm(@ModelAttribute(USER) JwtRequest jwtRequest, HttpServletResponse response) {
        ResponseEntity<JwtResponse> responseEntity = restApiClient.login(jwtRequest);
        String tokenCookie = HttpUtil.getHeader(responseEntity, COOKIE_HEADER);
        response.addHeader(COOKIE_HEADER, tokenCookie);
        return "redirect:/auth/hello";
    }
}
