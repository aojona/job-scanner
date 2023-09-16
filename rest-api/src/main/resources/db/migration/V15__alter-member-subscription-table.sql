ALTER TABLE member_subscription
    DROP CONSTRAINT member_subscription_member_id_fkey,
    ADD FOREIGN KEY (member_id) REFERENCES member(id) ON DELETE CASCADE,
    DROP CONSTRAINT member_subscription_subscription_id_fkey,
    ADD FOREIGN KEY (subscription_id) REFERENCES subscription(id) ON DELETE CASCADE