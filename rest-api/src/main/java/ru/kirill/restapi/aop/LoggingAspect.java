package ru.kirill.restapi.aop;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import java.util.Arrays;
import java.util.Objects;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

    @Pointcut("within(ru.kirill.restapi.service.*Service)")
    public void isServiceLayer() {}

    @Pointcut("within(ru.kirill.restapi.mapper.*Mapper)")
    public void isMapper() {}

    @Pointcut("@within(org.springframework.web.bind.annotation.RestController)")
    public void isControllerLayer() {}

    @Before("isServiceLayer() || isMapper()")
    public void beforeCommonLogging(JoinPoint joinPoint) {
        log.info("Executing {}.{}({})",
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                Arrays.toString(joinPoint.getArgs())
        );
    }

    @Around("isControllerLayer()")
    public Object controllerLayerLogging(ProceedingJoinPoint joinPoint) throws Throwable {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(requestAttributes)).getRequest();
        String remoteAddr = request.getRemoteAddr();
        String method = request.getMethod();
        String requestURI = request.getRequestURI();
        log.info("From {} request — method: {}, uri: {} , arguments: {}",
                remoteAddr,
                method,
                requestURI,
                Arrays.toString(joinPoint.getArgs())
        );
        try {
            Object result = joinPoint.proceed();
            log.info("To {} response — method: {}, uri: {} , result: {}",
                    remoteAddr,
                    method,
                    requestURI,
                    result.toString()
            );
            return result;
        } catch (Throwable e) {
            log.error("To {} exception — method: {}, uri: {} , exception: {}",
                    remoteAddr,
                    method,
                    requestURI,
                    e.getMessage()
            );
            throw e;
        }
    }
}
