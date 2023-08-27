package ru.kirill.webui.util;

import lombok.experimental.UtilityClass;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import java.util.Objects;

@UtilityClass
public class RequestUtil {

    public String getHeader(String header) {
        return Objects
                .requireNonNull((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest()
                .getHeader(header);
    }
}
