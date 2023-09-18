ALTER TABLE subscription
    ADD CONSTRAINT not_null_subscription CHECK (text IS NOT NULL);