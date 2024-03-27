package com.hkleev2.peacekeeper.controller.space;

import com.hkleev2.peacekeeper.controller.common.customException.CheckLoginException;
import com.hkleev2.peacekeeper.controller.common.customException.CheckSpaceAdminException;
import com.hkleev2.peacekeeper.controller.common.util.MemberUtil;
import com.hkleev2.peacekeeper.domain.space.model.Space;
import com.hkleev2.peacekeeper.domain.space.model.SpaceRequest;
import com.hkleev2.peacekeeper.service.space.ChangeSpaceService;
import com.hkleev2.peacekeeper.service.space.CreateSpaceService;
import com.hkleev2.peacekeeper.service.space.SpaceMemberService;
import com.hkleev2.peacekeeper.service.space.SpaceRequestService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/space")
@RestController
public class SpaceController {

    private final CreateSpaceService createSpaceService;
    private final SpaceMemberService spaceMemberService;
    private final SpaceRequestService spaceRequestService;
    private final ChangeSpaceService changeSpaceService;

    @PostMapping
    public Long create(@RequestBody CreateSpaceService.CreateSpaceParam param, SpaceMemberService.SpaceMemberParam param2, HttpServletRequest request) {

        HttpSession session = request.getSession();
        param.setAdminMemberId(MemberUtil.getLoginId(session));

        Long spaceId = createSpaceService.create(param);

        param2.setSpaceId(spaceId);
        spaceMemberService.addSpaceMember(param2);

        return spaceId;
    }

    @PatchMapping("/{spaceId}/spacename")
    public void changeSpacename(@PathVariable Long spaceId, @RequestBody ChangeSpaceService.ChangeSpacenameParam param, HttpServletRequest request) throws CheckSpaceAdminException {
        HttpSession session = request.getSession();
        if (changeSpaceService.checkSpaceAdmin(MemberUtil.getLoginId(session), spaceId)) {
            changeSpaceService.saveSpaceName(spaceId, param);
        }
    }

    @PostMapping("/createrequest")
    public Long createRequest(@RequestBody SpaceRequestService.SpaceRequestParam param) {
        return spaceRequestService.create(param);
    }

    @PatchMapping("/{spaceId}/{requestId}/updateRequestState")
    public SpaceRequest updateRequestState(@PathVariable Long spaceId, @PathVariable Long requestId, @RequestBody SpaceRequestService.changeRequestStateParam param, HttpServletRequest request) throws CheckSpaceAdminException {
        SpaceRequest spaceRequest = new SpaceRequest();

        HttpSession session = request.getSession();
        if (changeSpaceService.checkSpaceAdmin(MemberUtil.getLoginId(session), spaceId)) {
            spaceRequest = spaceRequestService.saveRequestState(requestId, param);
        }
        return spaceRequest;
    }

    @PostMapping("/addspacemember")
    public Long addSpaceMember(@RequestBody SpaceMemberService.SpaceMemberParam param) {
        return spaceMemberService.addSpaceMember(param);
    }

    @PostMapping("/{spaceId}/deletespacemember")
    public void deleteSpaceMember(@PathVariable Long spaceId, @RequestBody SpaceMemberService.SpaceMemberParam param, HttpServletRequest request) throws CheckSpaceAdminException {
        HttpSession session = request.getSession();
        if (changeSpaceService.checkSpaceAdmin(MemberUtil.getLoginId(session), spaceId)) {
            spaceMemberService.deleteSpaceMember(param);
        }
    }

    @PostMapping("/{spaceId}/changespaceadmin")
    public Space changeSpaceAdmin(@PathVariable Long spaceId, @RequestBody ChangeSpaceService.ChangeSpaceAdminParam param, HttpServletRequest request) throws CheckSpaceAdminException {
        Space space = new Space();

        HttpSession session = request.getSession();
        if (changeSpaceService.checkSpaceAdmin(MemberUtil.getLoginId(session), spaceId)) {
            space = changeSpaceService.changeSpaceAdmin(spaceId, param);
        }
        return space;
    }
}
