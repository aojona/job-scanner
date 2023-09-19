package ru.kirill.webui.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import ru.kirill.commondto.response.MemberResponse;
import ru.kirill.webui.feign.RestApiClient;

@Component
@RequiredArgsConstructor
public class AttributesProvider {

    private static final String MEMBER = "member";
    private static final String IS_AUTHENTICATED = "isAuthenticated";
    private static final String IS_ADMIN = "isAdmin";
    private static final String ADMIN_ROLE = "ADMIN";

    private final RestApiClient restApiClient;

    public void addDefaultMemberAttributes(Model model) {
        MemberResponse member = restApiClient.getMemberFromToken().getBody();
        if (member != null) {
            model.addAttribute(IS_AUTHENTICATED, true);
            model.addAttribute(IS_ADMIN, member.getRole().equals(ADMIN_ROLE));
            model.addAttribute(MEMBER, member);
        } else {
            model.addAttribute(IS_AUTHENTICATED, false);
            model.addAttribute(IS_ADMIN, false);
            model.addAttribute(MEMBER, new MemberResponse());
        }
    }
}
