package ru.kirill.restapi.util;

import lombok.experimental.UtilityClass;
import ru.kirill.restapi.entity.Subscription;

@UtilityClass
public class EntityUtil {

    public Subscription createSubscriptionWithText(String text) {
        Subscription subscription = new Subscription();
        subscription.setText(text);
        return subscription;
    }
}
