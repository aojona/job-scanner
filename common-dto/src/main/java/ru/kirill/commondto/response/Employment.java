package ru.kirill.commondto.response;

import lombok.Data;
import java.io.Serial;
import java.io.Serializable;

@Data
public class Employment implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String id;
    private String name;
}
