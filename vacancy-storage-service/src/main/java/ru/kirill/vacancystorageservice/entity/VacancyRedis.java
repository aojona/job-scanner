package ru.kirill.vacancystorageservice.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;
import ru.kirill.commondto.response.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@RedisHash(value = "vacancy")
public class VacancyRedis implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    private String name;
    private String url;
    @Indexed
    private LocalDate publishedAt;
    private Experience experience;
    private Employer employer;
    private Employment employment;
    private Salary salary;
    private Area area;
    private Type type;
    private Snippet snippet;
    @TimeToLive
    private long timeToLive;
}
