package ru.kirill.webui.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import ru.kirill.commondto.request.SubscriptionRequest;
import ru.kirill.commondto.response.MemberResponse;
import ru.kirill.webui.feign.RestApiClient;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final RestApiClient restApiClient;

    public void addMemberAttributes(Model model, String authAttribute, String memberAttribute) {
        ResponseEntity<MemberResponse> responseEntity = restApiClient.getMemberFromToken();
        if (responseEntity.hasBody()) {
            model.addAttribute(authAttribute, true);
            model.addAttribute(memberAttribute,responseEntity.getBody());
        } else {
            MemberResponse memberResponse = new MemberResponse();
            model.addAttribute(authAttribute, false);
            model.addAttribute(memberAttribute, memberResponse);
        }
    }

    public void deleteSubscription(SubscriptionRequest subscription) {
        restApiClient.deleteSubscription(subscription);
    }

    public void addSubscription(SubscriptionRequest subscription) {
        restApiClient.addSubscription(subscription);
    }
}
