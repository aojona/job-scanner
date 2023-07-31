package ru.kirill.schedulerservice.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
@Service
public @interface Scheduler {
    @AliasFor(annotation = Component.class)
    String value() default "";
}