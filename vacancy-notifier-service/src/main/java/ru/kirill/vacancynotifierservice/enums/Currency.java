package ru.kirill.vacancynotifierservice.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Getter
public enum Currency {

    AZN("Манаты"),
    BYR("Белорусские рубли"),
    EUR("Евро"),
    GEL("Грузинский лари"),
    KGS("Кыргызский сом"),
    KZT("Тенге"),
    RUR("Рубли"),
    USD("Доллары"),
    UZS("Узбекский сум");

    private final String description;

    Currency(String description) {
        this.description = description;
    }

    public static final String fieldName = "currency";
    private static final Map<String, Currency> currencyMap = new HashMap<>();

    static {
        Arrays
                .stream(Currency.values())
                .forEach(currency -> currencyMap.put(currency.name(), currency));
    }

    public static Optional<Currency> getCurrency(String name) {
        return Optional.ofNullable(currencyMap.get(name));
    }
}
