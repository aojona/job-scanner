CREATE FUNCTION subscription_insert_delete()
    RETURNS TRIGGER AS $subscription_insert_insert$
    BEGIN
        IF (TG_OP = 'INSERT') THEN
            UPDATE member_audit
            SET subscription_number = subscription_number + 1,
                modified_at = now()
            WHERE member_id = NEW.member_id;
            RETURN NEW;
        ELSEIF (TG_OP = 'DELETE') THEN
            UPDATE member_audit
            SET subscription_number = subscription_number - 1,
                modified_at = now()
            WHERE member_id = OLD.member_id;
            RETURN OLD;
        END IF;
        RETURN NULL;
    END;

$subscription_insert_insert$ LANGUAGE plpgsql;

CREATE TRIGGER subscription_insert_delete
    BEFORE INSERT OR DELETE ON subscription
    FOR EACH ROW EXECUTE PROCEDURE subscription_insert_delete()