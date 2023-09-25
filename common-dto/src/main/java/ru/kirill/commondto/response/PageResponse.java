package ru.kirill.commondto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Slice;

import java.util.List;

@Data
@AllArgsConstructor
@Schema(description = "Page with content")
public class PageResponse<D> {

    @Schema(description = "Data")
    private List<D> content;

    @Schema(description = "Metadata")
    private Metadata metadata;
    public static <D> PageResponse<D> of(Slice<D> slice) {
        return new PageResponse<>(slice.getContent(), getMetadata(slice));
    }

    @Data
    @Builder
    @Schema(description = "Metadata")
    private static class Metadata {

        @Schema(description = "Page number", example = "0")
        private int number;

        @Schema(description = "Elements number on one page", example = "1")
        private int size;

        @Schema(description = "False if the first page, otherwise - true")
        private boolean hasPrevious;

        @Schema(description = "False if the last page, otherwise - true")
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
