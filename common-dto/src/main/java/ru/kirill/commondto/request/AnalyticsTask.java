package ru.kirill.commondto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnalyticsTask implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String query;
}
