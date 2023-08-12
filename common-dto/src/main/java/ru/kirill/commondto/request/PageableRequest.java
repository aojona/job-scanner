package ru.kirill.commondto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Страница")
public class PageableRequest {

    @Schema(description = "Номер", example = "0")
    private final int pageNumber;

    @Schema(description = "Число элементов на странице", example = "1")
    private final int pageSize;
}
