package ru.kirill.commondto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccessResponse {

    private boolean access;
    private long nanosToWait;
    private long available;
}
