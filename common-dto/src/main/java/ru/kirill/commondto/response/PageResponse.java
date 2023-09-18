package ru.kirill.commondto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Slice;

import java.util.List;

@Data
@AllArgsConstructor
@Schema(description = "Страница с контентом")
public class PageResponse<D> {

    @Schema(description = "Данные")
    private List<D> content;

    @Schema(description = "Информация о странице с контентом")
    private Metadata metadata;
    public static <D> PageResponse<D> of(Slice<D> slice) {
        return new PageResponse<>(slice.getContent(), getMetadata(slice));
    }

    @Data
    @Builder
    @Schema(description = "Информация контенте")
    private static class Metadata {

        @Schema(description = "Номер страницы", example = "0")
        private int number;

        @Schema(description = "Размер страницы", example = "1")
        private int size;

        private boolean hasPrevious;

        private boolean hasNext;
    }

    private static <D> Metadata getMetadata(Slice<D> slice) {
        return Metadata
                .builder()
                .number(slice.getNumber())
                .size(slice.getSize())
                .hasPrevious(slice.hasPrevious())
                .hasNext(slice.hasNext())
                .build();
    }
}
