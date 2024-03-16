package com.hkleev2.peacekeeper.service.member;

import com.hkleev2.peacekeeper.domain.member.MemberRepository;
import com.hkleev2.peacekeeper.domain.member.model.Member;
import com.hkleev2.peacekeeper.domain.member.model.Nickname;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ChangeNicknameService {

    private final MemberRepository memberRepository;

    @Transactional
    public void changeNickname(Long memberId, String nicknameRaw) {
        Nickname nickname = Nickname.of(nicknameRaw);

        validateDuplicate(nickname);

        Member member = findMember(memberId);
        member.changeNickname(nickname);
    }

    private void validateDuplicate(Nickname nickname) {
        if (memberRepository.existsByNickname(nickname)) {
            throw new DuplicateNicknameException();
        }
    }

    private Member findMember(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow();
    }
}