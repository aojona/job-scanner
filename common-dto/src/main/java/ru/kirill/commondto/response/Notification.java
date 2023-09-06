package ru.kirill.commondto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notification {

    private Long chatId;
    private String text;
    private String logoUrl;
}
