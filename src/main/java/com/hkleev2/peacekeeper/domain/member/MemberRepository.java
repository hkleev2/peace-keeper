package com.hkleev2.peacekeeper.domain.member;

import com.hkleev2.peacekeeper.domain.member.model.Email;
import com.hkleev2.peacekeeper.domain.member.model.Member;
import com.hkleev2.peacekeeper.domain.member.model.Nickname;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    boolean existsByNickname(Nickname nickname);

    boolean existsByEmail(Email email);

}
