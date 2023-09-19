package ru.kirill.webui.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kirill.commondto.response.AverageVacancyStatistics;
import ru.kirill.commondto.response.Content;
import ru.kirill.webui.feign.RestApiClient;
import ru.kirill.webui.util.AttributesProvider;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private static final String STATISTICS = "statistics";

    private final RestApiClient restApiClient;
    private final AttributesProvider attributesProvider;

    @GetMapping
    public String homeView(Model model) {
        attributesProvider.addDefaultMemberAttributes(model);
        Content<AverageVacancyStatistics> statistics = restApiClient.getAverageStatistics().getBody();
        model.addAttribute(STATISTICS, statistics);
        return "index";
    }
}
