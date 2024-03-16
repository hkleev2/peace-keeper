package com.hkleev2.peacekeeper.domain.member;

import com.hkleev2.peacekeeper.domain.common.CreatedDateTime;
import com.hkleev2.peacekeeper.domain.member.model.*;
import com.hkleev2.peacekeeper.fixture.MemberFixture;
import org.junit.jupiter.api.DisplayName;
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

    @DisplayName("이메일 중복 확인")
    @Test
    void testDuplicatedEmail() {
        Member member = MemberFixture.member();
        memberRepository.save(member);

        boolean dup = memberRepository.existsByEmail(member.getEmail());
        assertThat(dup).isTrue();

        boolean notDup = memberRepository.existsByEmail(Email.of("notDup@test.com"));
        assertThat(notDup).isFalse();
    }

    @DisplayName("닉네임 중복 확인")
    @Test
    void testDuplicatedNickname() {
        Member member = MemberFixture.member();
        memberRepository.save(member);

        boolean dup = memberRepository.existsByNickname(member.getNickname());
        assertThat(dup).isTrue();

        boolean notDup = memberRepository.existsByNickname(Nickname.of("notDup"));
        assertThat(notDup).isFalse();
    }

}