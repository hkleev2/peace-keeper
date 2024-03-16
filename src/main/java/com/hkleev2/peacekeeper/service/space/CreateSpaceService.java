package com.hkleev2.peacekeeper.service.space;

import com.hkleev2.peacekeeper.domain.space.SpaceRepository;
import com.hkleev2.peacekeeper.domain.space.model.Space;
import com.hkleev2.peacekeeper.domain.space.model.Spacename;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class CreateSpaceService {

    private final SpaceRepository spaceRepository;

    @Transactional
    public Long create(CreateSpaceParam param) {
        Space space = param.toSpace();
        spaceRepository.save(space);
        return space.getId();
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class CreateSpaceParam {
        private Long adminMemberId;
        private String spaceKey;
        private String spacename;

        public Space toSpace() {
            return Space.builder()
                    .adminMemberId(adminMemberId)
                    .spaceKey(UUID.randomUUID().toString())
                    .spacename(Spacename.of(spacename))
                    .build();
        }

        public void setAdminMemberId(Long loginId) {
            adminMemberId = loginId;
        }
    }

}
