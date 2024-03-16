package com.hkleev2.peacekeeper.domain.member;

import com.hkleev2.peacekeeper.domain.member.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
