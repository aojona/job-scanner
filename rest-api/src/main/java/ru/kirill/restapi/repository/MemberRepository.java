package ru.kirill.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kirill.restapi.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
