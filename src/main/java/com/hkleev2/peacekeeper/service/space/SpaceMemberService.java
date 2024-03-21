package com.hkleev2.peacekeeper.service.space;

import com.hkleev2.peacekeeper.domain.space.SpaceMemberRepository;
import com.hkleev2.peacekeeper.domain.space.model.SpaceMember;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class SpaceMemberService {

    private final SpaceMemberRepository spaceMemberRepository;

    @Transactional
    public Long addSpaceMember(SpaceMemberParam param) {
        SpaceMember spaceMember = param.toSpaceMember();
        spaceMemberRepository.save(spaceMember);
        return spaceMember.getId();
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class SpaceMemberParam {
        private Long spaceId;
        private Long memberId;

        public SpaceMember toSpaceMember() {
            return SpaceMember.builder()
                    .spaceId(spaceId)
                    .memberId(memberId)
                    .build();
        }

        public void setSpaceId(Long id) {
            spaceId = id;
        }
    }

}
