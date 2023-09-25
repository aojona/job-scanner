package ru.kirill.commondto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Page")
public class PageableRequest {

    @Schema(description = "Number", example = "0")
    private final int pageNumber;

    @Schema(description = "Elements number on one page", example = "1")
    private final int pageSize;
}
