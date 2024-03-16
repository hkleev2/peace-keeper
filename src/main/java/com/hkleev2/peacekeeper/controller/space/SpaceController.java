package com.hkleev2.peacekeeper.controller.space;

import com.hkleev2.peacekeeper.controller.common.customException.CheckLoginException;
import com.hkleev2.peacekeeper.controller.common.util.MemberUtil;
import com.hkleev2.peacekeeper.service.space.CreateSpaceService;
import com.hkleev2.peacekeeper.service.space.SpaceMemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/space")
@RestController
public class SpaceController {

    private final CreateSpaceService createSpaceService;
    private final SpaceMemberService spaceMemberService;

    @PostMapping
    public Long create(@RequestBody CreateSpaceService.CreateSpaceParam param, SpaceMemberService.SpaceMemberParam param2, HttpServletRequest request) throws CheckLoginException {

        HttpSession session = request.getSession();
        MemberUtil.chkLogin(session);
        param.setAdminMemberId(MemberUtil.getLoginId(session));

        Long spaceId = createSpaceService.create(param);

        param2.setSpaceId(spaceId);
        spaceMemberService.spaceMember(param2);

        return spaceId;
    }

    @PostMapping
    public Long spaceMember(@RequestBody SpaceMemberService.SpaceMemberParam param, HttpServletRequest request) throws CheckLoginException {
        HttpSession session = request.getSession();
        MemberUtil.chkLogin(session);

        return spaceMemberService.spaceMember(param);
    }
}
