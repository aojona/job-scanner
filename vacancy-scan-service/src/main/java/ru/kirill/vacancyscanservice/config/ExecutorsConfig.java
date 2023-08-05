package ru.kirill.vacancyscanservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@Configuration
public class ExecutorsConfig {

    @Bean
    public ScheduledExecutorService executorService(@Value("${executors.threads.scheduled}") int threadNumber) {
        return Executors.newScheduledThreadPool(threadNumber);
    }
}
