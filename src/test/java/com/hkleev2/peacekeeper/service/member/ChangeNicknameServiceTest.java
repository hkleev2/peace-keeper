package com.hkleev2.peacekeeper.service.member;

import com.hkleev2.peacekeeper.domain.member.MemberRepository;
import com.hkleev2.peacekeeper.domain.member.model.Email;
import com.hkleev2.peacekeeper.domain.member.model.Member;
import com.hkleev2.peacekeeper.domain.member.model.Nickname;
import com.hkleev2.peacekeeper.fixture.MemberFixture;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@DisplayName("회원 닉네임 변경")
@Transactional
@SpringBootTest
public class ChangeNicknameServiceTest {

    @Autowired
    private ChangeNicknameService changeNicknameService;

    @Autowired
    private MemberRepository memberRepository;

    @DisplayName("중복된 닉네임으로는 변경할 수 없다.")
    @Test
    void cannotChangeNicknameWithDuplicate() {
        Member givenMember = MemberFixture.member();
        memberRepository.save(givenMember);

        Member newMember = Member.builder()
                .email(Email.of("new@new.com"))
                .nickname(Nickname.of("notDup"))
                .build();
        memberRepository.save(newMember);

        ChangeNicknameService.ChangeNicknameParam param = ChangeNicknameService.ChangeNicknameParam.builder()
                .nickname(givenMember.getNickname().getValue())
                .build();

        Assertions.assertThatThrownBy(() -> changeNicknameService.changeNickname(newMember.getId(), param))
                .isExactlyInstanceOf(DuplicateNicknameException.class);
    }

    @DisplayName("중복되지 않은 닉네임으로는 변경할 수 있다.")
    @Test
    void canChangeNicknameWithNonDuplicate() {
        Member givenMember = MemberFixture.member();
        memberRepository.save(givenMember);

        Member newMember = Member.builder()
                .email(Email.of("new@new.com"))
                .nickname(Nickname.of("notDup"))
                .build();
        memberRepository.save(newMember);

        String notDupNickname = "notDup2";

        ChangeNicknameService.ChangeNicknameParam param = ChangeNicknameService.ChangeNicknameParam.builder()
                .nickname(notDupNickname)
                .build();

        changeNicknameService.changeNickname(newMember.getId(), param);

        Member updatedMember = memberRepository.findById(newMember.getId()).orElseThrow();
        assertThat(updatedMember.getNickname().getValue()).isEqualTo(notDupNickname);
    }

}
