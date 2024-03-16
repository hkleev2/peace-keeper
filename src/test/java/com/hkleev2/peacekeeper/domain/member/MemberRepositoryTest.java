package com.hkleev2.peacekeeper.domain.member;

import com.hkleev2.peacekeeper.domain.common.CreatedDateTime;
import com.hkleev2.peacekeeper.domain.member.model.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    void createMember() {
        String mail = "test@test.com";
        String nickname = "nickname";

        Member member = Member.builder()
                .nickname(Nickname.of(nickname))
                .email(Email.of(mail))
                .createdDateTime(CreatedDateTime.of(LocalDateTime.now()))
                .build();

        memberRepository.save(member);

        assertThat(member.getId()).isNotNull();
        assertThat(member.getEmail()).isEqualTo(Email.of(mail));
    }
}