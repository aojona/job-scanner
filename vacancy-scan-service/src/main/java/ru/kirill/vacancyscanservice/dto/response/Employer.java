package ru.kirill.vacancyscanservice.dto.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Employer extends BaseResponse {

    private String url;
    private String alternateUrl;
    private String vacanciesUrl;
    private Boolean accreditedItEmployer;
    private Boolean trusted;
}
