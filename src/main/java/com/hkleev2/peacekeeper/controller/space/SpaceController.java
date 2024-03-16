package com.hkleev2.peacekeeper.controller.space;

import com.hkleev2.peacekeeper.controller.common.customException.CheckLoginException;
import com.hkleev2.peacekeeper.controller.common.util.MemberUtil;
import com.hkleev2.peacekeeper.service.space.CreateSpaceService;
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

    @PostMapping
    public Long create(@RequestBody CreateSpaceService.CreateSpaceParam param, HttpServletRequest request) throws CheckLoginException {

        HttpSession session = request.getSession();
        MemberUtil.chkLogin(session);

        param.setAdminMemberId(MemberUtil.getLoginId(session));
        return createSpaceService.create(param);
    }
}
