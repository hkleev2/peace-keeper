package com.hkleev2.peacekeeper.service.member;

import com.hkleev2.peacekeeper.domain.member.MemberRepository;
import com.hkleev2.peacekeeper.domain.member.model.Email;
import com.hkleev2.peacekeeper.domain.member.model.Member;
import com.hkleev2.peacekeeper.domain.member.model.Nickname;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CreateMemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long create(CreateMemberParam param) {
        Member member = param.toMember();
        memberRepository.save(member);
        return member.getId();
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class CreateMemberParam {
        private String email;
        private String nickname;

        public Member toMember() {
            return Member.builder()
                    .email(Email.of(email))
                    .nickname(Nickname.of(nickname))
                    .build();
        }
    }

}
