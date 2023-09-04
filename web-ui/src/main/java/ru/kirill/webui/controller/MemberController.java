package ru.kirill.webui.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kirill.webui.service.MemberService;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private static final String MEMBER = "member";
    private static final String IS_AUTHENTICATED = "isAuthenticated";

    private final MemberService memberService;

    @GetMapping("/")
    public String homeView(Model model) {
        memberService.addMemberAttributes(model, IS_AUTHENTICATED, MEMBER);
        return "index";
    }
}
