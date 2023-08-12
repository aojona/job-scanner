package ru.kirill.commondto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;
import java.util.List;

@Data
@AllArgsConstructor
@Schema(description = "Страница с контентом")
public class PageResponse<D> {

    @Schema(description = "Данные")
    private List<D> content;

    @Schema(description = "Информация о странице с контентом")
    private Metadata metadata;

    public static <D> PageResponse<D> of(Page<D> page) {
        return new PageResponse<>(page.getContent(), getMetadata(page));
    }

    @Data
    @Builder
    @Schema(description = "Информация контенте")
    private static class Metadata {

        @Schema(description = "Номер страницы", example = "0")
        private int number;

        @Schema(description = "Размер страницы", example = "1")
        private int size;

        @Schema(description = "Общее число результатов", example = "10")
        private long totalElements;
    }

    private static <D> Metadata getMetadata(Page<D> page) {
        return Metadata
                .builder()
                .number(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .build();
    }
}
