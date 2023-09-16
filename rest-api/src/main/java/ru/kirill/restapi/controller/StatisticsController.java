package ru.kirill.restapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kirill.commondto.response.AverageVacancyStatistics;
import ru.kirill.commondto.response.Content;
import ru.kirill.commondto.response.ContentMap;
import ru.kirill.commondto.response.StatisticsResponse;
import ru.kirill.restapi.security.JwtAuthentication;
import ru.kirill.restapi.service.StatisticsService;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/statistics")
public class StatisticsController {

    private final StatisticsService statisticsService;

    @GetMapping("/random")
    public ResponseEntity<Content<AverageVacancyStatistics>> getAverageStatistics() {
        List<AverageVacancyStatistics> statistics = statisticsService.getStatisticsForRandomSubscriptions();
        return new ResponseEntity<>(new Content<>(statistics), HttpStatus.OK);
    }

    @GetMapping("/member")
    public ResponseEntity<ContentMap<String, List<StatisticsResponse>>> getMemberStatistics(JwtAuthentication authentication) {
        Map<String, List<StatisticsResponse>> statistics = statisticsService
                .getStatisticForMemberSubscriptions(authentication.getPrincipal().getId());
        return new ResponseEntity<>(new ContentMap<>(statistics), HttpStatus.OK);
    }
}
