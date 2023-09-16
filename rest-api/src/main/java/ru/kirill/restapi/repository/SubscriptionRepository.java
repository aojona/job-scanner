package ru.kirill.restapi.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.kirill.restapi.entity.Subscription;

import java.util.List;
import java.util.Optional;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    @EntityGraph(attributePaths = "members")
    Optional<Subscription> findByText(String text);

    @Query("SELECT s FROM Subscription s ORDER BY RANDOM()")
    List<Subscription> findRandomSubscriptions(Pageable pageable);
}
