package ru.kirill.webui.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kirill.commondto.request.SubscriptionRequest;
import ru.kirill.webui.service.MemberService;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private static final String MEMBER = "member";
    private static final String SUBSCRIPTION = "subscription";
    private static final String IS_AUTHENTICATED = "isAuthenticated";

    private final MemberService memberService;

    @GetMapping("/")
    public String homeView(Model model) {
        memberService.addMemberAttributes(model, IS_AUTHENTICATED, MEMBER);
        return "index";
    }

    @GetMapping("/member")
    public String memberView(Model model) {
        memberService.addMemberAttributes(model, IS_AUTHENTICATED, MEMBER);
        model.addAttribute(SUBSCRIPTION, new SubscriptionRequest());
        return "member";
    }

    @PostMapping("/member")
    public String addSubscription(@ModelAttribute(SUBSCRIPTION) SubscriptionRequest subscription) {
        memberService.addSubscription(subscription);
        return "redirect:/member";
    }

    @DeleteMapping("/member")
    public String removeSubscription(@ModelAttribute(SUBSCRIPTION) SubscriptionRequest subscription) {
        memberService.deleteSubscription(subscription);
        return "redirect:/member";
    }
}
