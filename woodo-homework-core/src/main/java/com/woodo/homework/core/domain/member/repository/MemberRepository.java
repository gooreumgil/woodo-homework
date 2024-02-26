package com.woodo.homework.core.domain.member.repository;

import com.woodo.homework.core.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
