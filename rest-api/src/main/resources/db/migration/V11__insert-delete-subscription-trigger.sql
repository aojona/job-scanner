DROP TRIGGER IF EXISTS subscription_insert_delete on subscription;

CREATE TRIGGER subscription_insert_delete
    BEFORE INSERT OR DELETE ON member_subscription
    FOR EACH ROW EXECUTE PROCEDURE subscription_insert_delete()