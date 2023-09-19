package ru.kirill.webui.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kirill.commondto.request.ChatRequest;
import ru.kirill.commondto.request.SubscriptionRequest;
import ru.kirill.commondto.response.*;
import ru.kirill.webui.feign.RestApiClient;
import ru.kirill.webui.util.AttributesProvider;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private static final String SUBSCRIPTION = "subscription";
    private static final String CHAT_ID = "chat";
    private static final String STATISTICS = "statistics";

    private final RestApiClient restApiClient;
    private final AttributesProvider attributesProvider;

    @GetMapping
    public String memberView(Model model) {
        attributesProvider.addDefaultMemberAttributes(model);
        ContentMap<String, List<StatisticsResponse>> statistics = restApiClient.getMemberStatistics().getBody();
        model.addAttribute(STATISTICS, statistics);
        model.addAttribute(SUBSCRIPTION, new SubscriptionRequest());
        model.addAttribute(CHAT_ID, new ChatRequest());
        return "member";
    }

    @PostMapping("/addSubscription")
    public String addSubscription(@ModelAttribute(SUBSCRIPTION) SubscriptionRequest subscription) {
        restApiClient.addSubscription(subscription);
        return "redirect:/member";
    }

    @PostMapping("/addChatId")
    public String addTelegramChatId(@ModelAttribute(CHAT_ID) ChatRequest chatRequest) {
        restApiClient.updateChatId(chatRequest);
        return "redirect:/member";
    }

    @DeleteMapping("/removeSubscription")
    public String removeSubscription(@ModelAttribute(SUBSCRIPTION) SubscriptionRequest subscription) {
        restApiClient.removeSubscription(subscription);
        return "redirect:/member";
    }
}
