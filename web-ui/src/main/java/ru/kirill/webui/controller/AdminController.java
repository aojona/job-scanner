package ru.kirill.webui.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kirill.commondto.request.MemberRequest;
import ru.kirill.commondto.request.PageableRequest;
import ru.kirill.commondto.request.SubscriptionRequest;
import ru.kirill.commondto.response.MemberResponse;
import ru.kirill.commondto.response.PageResponse;
import ru.kirill.commondto.response.SubscriptionResponse;
import ru.kirill.webui.config.PageRequestProperties;
import ru.kirill.webui.feign.RestApiClient;
import ru.kirill.webui.util.AttributesProvider;

@Controller
@RequiredArgsConstructor
public class AdminController {

    private static final String SUBSCRIPTION_PAGE = "subscription_page";
    private static final String SUBSCRIPTION = "subscription";
    private static final String MEMBER_PAGE = "member_page";
    private static final String USER = "user";

    private final RestApiClient restApiClient;
    private final PageRequestProperties pageRequestProperties;
    private final AttributesProvider attributesProvider;

    @GetMapping("/admin")
    public String controlView(Model model,
                              @RequestParam(required = false) Integer memberPage,
                              @RequestParam(required = false) Integer subscriptionPage) {
        attributesProvider.addDefaultMemberAttributes(model);
        PageResponse<SubscriptionResponse> subscriptions = restApiClient.getSubscriptions(createRequest(subscriptionPage)).getBody();
        PageResponse<MemberResponse> members = restApiClient.getMembers(createRequest(memberPage)).getBody();
        model.addAttribute(SUBSCRIPTION_PAGE, subscriptions);
        model.addAttribute(SUBSCRIPTION, new SubscriptionRequest());
        model.addAttribute(MEMBER_PAGE, members);
        model.addAttribute(USER, new MemberRequest());
        return "admin";
    }

    @DeleteMapping("/admin/deleteSubscription")
    public String deleteSubscription(@ModelAttribute(SUBSCRIPTION) SubscriptionRequest subscription) {
        restApiClient.deleteSubscription(subscription);
        return "redirect:/admin";
    }

    @DeleteMapping("/admin/deleteMember")
    public String deleteMember(@ModelAttribute(USER) MemberRequest member) {
        restApiClient.deleteMember(member);
        return "redirect:/admin";
    }

    private PageableRequest createRequest(Integer page) {
        return page != null
                ? new PageableRequest(page, pageRequestProperties.request().getPageSize())
                : pageRequestProperties.request();
    }
}
