package ru.kirill.webui.config;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kirill.commondto.request.PageableRequest;
import ru.kirill.commondto.request.SubscriptionRequest;
import ru.kirill.commondto.response.PageResponse;
import ru.kirill.commondto.response.SubscriptionResponse;
import ru.kirill.webui.feign.RestApiClient;
import ru.kirill.webui.util.AttributesProvider;

@Controller
@RequiredArgsConstructor
public class AdminController {

    private static final String SUBSCRIPTION_PAGE = "subscription_page";
    private static final String SUBSCRIPTION = "subscription";

    private final RestApiClient restApiClient;
    private final PageRequestProperties pageRequestProperties;
    private final AttributesProvider attributesProvider;

    @GetMapping("/admin")
    public String controlView(Model model, @RequestParam(required = false) Integer subscriptionPage) {
        attributesProvider.addDefaultMemberAttributes(model);
        PageableRequest request = subscriptionPage != null
                ? new PageableRequest(subscriptionPage, pageRequestProperties.request().getPageSize())
                : pageRequestProperties.request();
        PageResponse<SubscriptionResponse> response =  restApiClient.getAll(request).getBody();
        model.addAttribute(SUBSCRIPTION_PAGE, response);
        model.addAttribute(SUBSCRIPTION, new SubscriptionRequest());
        return "admin";
    }
}
