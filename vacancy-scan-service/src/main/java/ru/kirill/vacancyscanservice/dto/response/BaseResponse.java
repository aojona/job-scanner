package ru.kirill.vacancyscanservice.dto.response;

import lombok.Data;

@Data
public abstract class BaseResponse {

    private String id;
    private String name;
}
