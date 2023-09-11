CREATE TABLE vacancy_statistics
(
    id                              BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    query                           VARCHAR(32) NOT NULL,
    average_min_salary              DOUBLE PRECISION,
    average_max_salary              DOUBLE PRECISION,
    number_of_vacancies             INT,
    number_of_vacancies_with_salary INT
);