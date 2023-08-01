package ru.kirill.vacancystorageservice.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import ru.kirill.commondto.response.*;
import java.sql.Timestamp;

@Data
@RedisHash("vacancy")
public class VacancyRedis {

    @Id
    private String id;
    private String name;
    private String url;
    private Timestamp publishedAt;
    private Experience experience;
    private Employer employer;
    private Employment employment;
    private Salary salary;
    private Area area;
    private Type type;
    private Snippet snippet;
}
