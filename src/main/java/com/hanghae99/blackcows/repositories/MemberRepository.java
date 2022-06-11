package com.hanghae99.blackcows.repositories;

import com.hanghae99.blackcows.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}