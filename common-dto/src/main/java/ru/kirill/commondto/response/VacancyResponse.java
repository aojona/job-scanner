package ru.kirill.commondto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VacancyResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private List<Long> chatIds;
    private Vacancy vacancy;
    private String queryText;
}
