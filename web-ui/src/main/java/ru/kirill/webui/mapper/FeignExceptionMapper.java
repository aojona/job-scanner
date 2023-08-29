package ru.kirill.webui.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import ru.kirill.commondto.response.ExceptionResponse;

@Component
@RequiredArgsConstructor
public class FeignExceptionMapper {

    private final ObjectMapper objectMapper;

    @SneakyThrows
    public ExceptionResponse convert(FeignException exception) {
        return objectMapper.readValue(exception.contentUTF8(), ExceptionResponse.class);
    }
}
