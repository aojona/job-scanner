package ru.kirill.restapi.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.kirill.restapi.entity.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByUsername(String username);

    @Modifying
    @Query("UPDATE Member m SET m.telegramChatId = :telegramChatId where m.id = :id")
    void updateChatId(long id, Long telegramChatId);

    @EntityGraph(attributePaths = "subscriptions")
    Optional<Member> findMemberWithSubscriptionsById(Long id);

    @Query("SELECT m FROM Member m ORDER BY m.username")
    @EntityGraph(attributePaths = "subscriptions")
    Slice<Member> findAllSlice(Pageable pageable);
}
