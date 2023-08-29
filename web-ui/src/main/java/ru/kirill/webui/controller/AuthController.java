package ru.kirill.webui.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kirill.commondto.request.JwtRequest;
import ru.kirill.webui.feign.RestApiClient;

@Controller
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

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
}
