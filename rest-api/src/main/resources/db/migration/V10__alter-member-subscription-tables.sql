ALTER TABLE subscription
DROP COLUMN member_id;

ALTER TABLE subscription
ADD CONSTRAINT unique_request UNIQUE(text);

CREATE TABLE member_subscription
(
    member_id BIGINT NOT NULL REFERENCES member(id),
    subscription_id  BIGINT NOT NULL REFERENCES  subscription(id),
    UNIQUE (member_id, subscription_id)
)