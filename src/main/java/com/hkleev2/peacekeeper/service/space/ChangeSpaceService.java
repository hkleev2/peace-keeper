package com.hkleev2.peacekeeper.service.space;

import com.hkleev2.peacekeeper.controller.common.customException.CheckSpaceAdminException;
import com.hkleev2.peacekeeper.domain.space.SpaceRepository;
import com.hkleev2.peacekeeper.domain.space.model.Space;
import com.hkleev2.peacekeeper.domain.space.model.Spacename;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ChangeSpaceService {

    private final SpaceRepository spaceRepository;

    public Boolean checkSpaceAdmin(Long loginId, Long spaceId) throws CheckSpaceAdminException {
        Space space = spaceRepository.findById(spaceId).orElseThrow();

        if (space.getAdminMemberId() == loginId) {
            return true;
        } else {
            throw new CheckSpaceAdminException();
        }
    }

    @Transactional
    public void changeSpacename(Long spaceId, ChangeSpacenameParam param) {
        Spacename spacename = Spacename.of(param.getSpacename());

        Space space = findSpace(spaceId);
        space.changeSpacekname(spacename);
    }

    private Space findSpace(Long spaceId) {
        return spaceRepository.findById(spaceId).orElseThrow();
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class ChangeSpacenameParam {
        private String spacename;
    }
}
