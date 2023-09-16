package ru.kirill.commondto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContentMap<C1, C2> {
    private Map<C1, C2> content;
}
