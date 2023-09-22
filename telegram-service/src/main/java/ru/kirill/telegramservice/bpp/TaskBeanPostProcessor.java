package ru.kirill.telegramservice.bpp;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import ru.kirill.telegramservice.listener.NotificationConsumer;

@Component
@RequiredArgsConstructor
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class TaskBeanPostProcessor implements BeanPostProcessor {

    private final ExceptionAdvice exceptionAdvice;

    @Override
    public Object postProcessBeforeInitialization(Object bean, @NonNull String beanName) throws BeansException {
        if (bean.getClass() == NotificationConsumer.class) {
            ProxyFactory factory = new ProxyFactory(bean);
            factory.addAdvice(exceptionAdvice);
            return factory.getProxy();
        }
        return bean;
    }
}
