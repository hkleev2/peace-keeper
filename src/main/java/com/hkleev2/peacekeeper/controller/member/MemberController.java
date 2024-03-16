package com.hkleev2.peacekeeper.controller.member;

import com.hkleev2.peacekeeper.service.member.CreateMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/members")
@RestController
public class MemberController {

    private final CreateMemberService createMemberService;

    @PostMapping
    public Long create(@RequestBody CreateMemberService.CreateMemberParam param) {
        return createMemberService.create(param);
    }

}
