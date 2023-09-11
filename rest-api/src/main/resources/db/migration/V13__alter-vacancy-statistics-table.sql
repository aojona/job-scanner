ALTER TABLE vacancy_statistics
ADD COLUMN date DATE;

ALTER TABLE vacancy_statistics
ADD CONSTRAINT unique_statistics UNIQUE(query, date)