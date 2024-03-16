package com.hkleev2.peacekeeper.controller.member;

import com.hkleev2.peacekeeper.service.member.ChangeNicknameService;
import com.hkleev2.peacekeeper.service.member.CreateMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/members")
@RestController
public class MemberController {

    private final CreateMemberService createMemberService;
    private final ChangeNicknameService changeNicknameService;

    @PostMapping
    public Long create(@RequestBody CreateMemberService.CreateMemberParam param) {
        return createMemberService.create(param);
    }

    @PatchMapping("/{memberId}/nickname")
    public void changeNickname(@PathVariable Long memberId, @RequestBody ChangeNicknameService.ChangeNicknameParam param) {
        // TODO 요청자 memberId 일치 여부 체크 필요
        changeNicknameService.changeNickname(memberId, param);
    }

}
