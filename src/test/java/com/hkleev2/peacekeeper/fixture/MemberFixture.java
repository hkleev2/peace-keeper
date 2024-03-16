package com.hkleev2.peacekeeper.fixture;

import com.hkleev2.peacekeeper.domain.member.model.Email;
import com.hkleev2.peacekeeper.domain.member.model.Member;
import com.hkleev2.peacekeeper.domain.member.model.Nickname;

public class MemberFixture {

    public static Member member() {
        return member("test@test.com", "tester");
    }

    public static Member member(String email, String nickname) {
        return Member.builder()
                .nickname(Nickname.of(nickname))
                .email(Email.of(email))
                .build();
    }

}
