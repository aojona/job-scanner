package ru.kirill.webui.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kirill.commondto.request.ChatRequest;
import ru.kirill.commondto.request.SubscriptionRequest;
import ru.kirill.commondto.response.*;
import ru.kirill.webui.feign.RestApiClient;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private static final String MEMBER = "member";
    private static final String SUBSCRIPTION = "subscription";
    private static final String IS_AUTHENTICATED = "isAuthenticated";
    private static final String CHAT = "chat";
    private static final String STATISTICS = "statistics";

    private final RestApiClient restApiClient;

    @GetMapping("/")
    public String homeView(Model model) {
        addDefaultMemberAttributes(model);
        Content<AverageVacancyStatistics> statistics = restApiClient.getAverageStatistics().getBody();
        model.addAttribute(STATISTICS, statistics);
        return "index";
    }

    @GetMapping("/member")
    public String memberView(Model model) {
        addDefaultMemberAttributes(model);
        ContentMap<String, List<StatisticsResponse>> statistics = restApiClient.getMemberStatistics().getBody();
        model.addAttribute(STATISTICS, statistics);
        model.addAttribute(SUBSCRIPTION, new SubscriptionRequest());
        model.addAttribute(CHAT, new ChatRequest());
        return "member";
    }

    @PostMapping("/member/addSubscription")
    public String addSubscription(@ModelAttribute(SUBSCRIPTION) SubscriptionRequest subscription) {
        restApiClient.addSubscription(subscription);
        return "redirect:/member";
    }

    @PostMapping("/member/addChatId")
    public String addTelegramChatId(@ModelAttribute(CHAT) ChatRequest chatRequest) {
        restApiClient.updateChatId(chatRequest);
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
            model.addAttribute(IS_AUTHENTICATED, false);
            model.addAttribute(MEMBER, new MemberResponse());
        }
    }
}
