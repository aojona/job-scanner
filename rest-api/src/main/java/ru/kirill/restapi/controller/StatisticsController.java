package ru.kirill.restapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kirill.commondto.response.AverageVacancyStatistics;
import ru.kirill.commondto.response.Content;
import ru.kirill.restapi.service.StatisticsService;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/statistics")
public class StatisticsController {

    private final StatisticsService statisticsService;

    @GetMapping
    public ResponseEntity<Content<AverageVacancyStatistics>> getAverageStatistics() {
        List<AverageVacancyStatistics> statistics = statisticsService.getStatisticsForRandomSubscriptions();
        return new ResponseEntity<>(new Content<>(statistics), HttpStatus.OK);
    }

}
