package ru.kirill.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kirill.restapi.entity.Subscription;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
}
