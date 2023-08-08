package ru.kirill.commondto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;
import java.util.List;

@Data
@AllArgsConstructor
public class PageResponse<D> {

    private List<D> content;
    private Metadata metadata;

    public static <D> PageResponse<D> of(Page<D> page) {
        return new PageResponse<>(page.getContent(), getMetadata(page));
    }

    @Data
    @Builder
    private static class Metadata {
        private int number;
        private int size;
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
