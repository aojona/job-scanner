package ru.kirill.vacancyscanservice.bpp;

import feign.RetryableException;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import ru.kirill.vacancyscanservice.VacancyScanServiceApplication;

@Slf4j
@Component
public class ExceptionAdvice implements MethodInterceptor {
    @Override
    public Object invoke(@NotNull MethodInvocation invocation) {
        try {
            return invocation.proceed();
        } catch (Throwable e) {
            if (e instanceof RetryableException) {
                log.error("Unable to connect to rate-limiter-service");
                VacancyScanServiceApplication.close();
            }
            return null;
        }
    }
}

