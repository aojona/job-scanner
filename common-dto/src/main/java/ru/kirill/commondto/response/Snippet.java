package ru.kirill.commondto.response;

import lombok.Data;
import java.io.Serial;
import java.io.Serializable;

@Data
public class Snippet implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String requirement;
    private String responsibility;
}
