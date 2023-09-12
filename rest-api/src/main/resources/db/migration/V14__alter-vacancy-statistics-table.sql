ALTER TABLE vacancy_statistics
    DROP CONSTRAINT unique_statistics;

CREATE UNIQUE INDEX statistics_query_date_index
    ON vacancy_statistics (query, date)