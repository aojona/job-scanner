package ru.kirill.commondto.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class ZonedDateTimeDeserializer extends JsonDeserializer<ZonedDateTime> {
    @Override
    public ZonedDateTime deserialize(JsonParser jsonParser,
                                     DeserializationContext deserializationContext) throws IOException {
        return ZonedDateTime.parse(
                jsonParser.getText(),
                DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ")
        );
    }
}
