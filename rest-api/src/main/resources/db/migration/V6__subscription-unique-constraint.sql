ALTER TABLE subscription
ADD CONSTRAINT unique_subscription UNIQUE(request, member_id);