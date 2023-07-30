package ru.kirill.commondto.response;

import lombok.Data;

@Data
public abstract class BaseResponse {

    private String id;
    private String name;
}
