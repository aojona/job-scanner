package ru.kirill.commondto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Content<C> {
    private List<C> content;
}
