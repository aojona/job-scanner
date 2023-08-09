CREATE FUNCTION member_insert()
    RETURNS TRIGGER AS $member_insert$
    BEGIN
        INSERT INTO member_audit(member_id, subscription_number, created_at)
        VALUES (NEW.id, 0, now());
        RETURN NEW;
    END;

$member_insert$ LANGUAGE plpgsql;

CREATE TRIGGER member_insert
    BEFORE INSERT ON member
    FOR EACH ROW EXECUTE PROCEDURE member_insert();