package ru.kirill.webui.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kirill.commondto.request.SubscriptionRequest;
import ru.kirill.commondto.response.MemberResponse;
import ru.kirill.webui.feign.RestApiClient;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private static final String MEMBER = "member";
    private static final String SUBSCRIPTION = "subscription";
    private static final String IS_AUTHENTICATED = "isAuthenticated";

    private final RestApiClient restApiClient;

    @GetMapping("/")
    public String homeView(Model model) {
        addDefaultMemberAttributes(model);
        return "index";
    }

    @GetMapping("/member")
    public String memberView(Model model) {
        addDefaultMemberAttributes(model);
        model.addAttribute(SUBSCRIPTION, new SubscriptionRequest());
        return "member";
    }

    @PostMapping("/member/addSubscription")
    public String addSubscription(@ModelAttribute(SUBSCRIPTION) SubscriptionRequest subscription) {
        restApiClient.addSubscription(subscription);
        return "redirect:/member";
    }

    @DeleteMapping("/member/removeSubscription")
    public String removeSubscription(@ModelAttribute(SUBSCRIPTION) SubscriptionRequest subscription) {
        restApiClient.deleteSubscription(subscription);
        return "redirect:/member";
    }

    private void addDefaultMemberAttributes(Model model) {
        ResponseEntity<MemberResponse> responseEntity = restApiClient.getMemberFromToken();
        if (responseEntity.hasBody()) {
            model.addAttribute(IS_AUTHENTICATED, true);
            model.addAttribute(MEMBER, responseEntity.getBody());
        } else {
            MemberResponse memberResponse = new MemberResponse();
            model.addAttribute(IS_AUTHENTICATED, false);
            model.addAttribute(MEMBER, memberResponse);
        }
    }
}
