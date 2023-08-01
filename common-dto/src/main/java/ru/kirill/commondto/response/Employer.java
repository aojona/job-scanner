package ru.kirill.commondto.response;

import lombok.Data;
import java.io.Serial;
import java.io.Serializable;

@Data
public class Employer implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String id;
    private String name;
    private String url;
    private String alternateUrl;
    private String vacanciesUrl;
    private Boolean accreditedItEmployer;
    private Boolean trusted;
}
