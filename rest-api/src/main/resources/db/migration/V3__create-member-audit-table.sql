CREATE TABLE member_audit
(
    member_id               BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    subscription_number     INT NOT NULL,
    created_at              TIMESTAMP NOT NULL,
    modified_at             TIMESTAMP
)