package com.hkleev2.peacekeeper.service.space;

import com.hkleev2.peacekeeper.domain.space.SpaceRequestRepository;
import com.hkleev2.peacekeeper.domain.space.model.RequestState;
import com.hkleev2.peacekeeper.domain.space.model.Space;
import com.hkleev2.peacekeeper.domain.space.model.SpaceMember;
import com.hkleev2.peacekeeper.domain.space.model.SpaceRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class SpaceRequestService {

    private final SpaceRequestRepository spaceRequestRepository;

    @Transactional
    public Long create(SpaceRequestParam param) {
        SpaceRequest spaceRequest = param.toSpaceRequest();
        spaceRequestRepository.save(spaceRequest);
        return spaceRequest.getId();
    }

    @Transactional
    public SpaceRequest saveRequestState(Long requestId, changeRequestStateParam param) {
        RequestState requestState = RequestState.of(param.getState());

        SpaceRequest spaceRequest = findRequest(requestId);
        spaceRequest.changeState(requestState);

        return spaceRequest;
    }

    private SpaceRequest findRequest(Long requestId) {
        return spaceRequestRepository.findById(requestId).orElseThrow();
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class SpaceRequestParam {
        private Long spaceId;
        private Long memberId;

        public SpaceRequest toSpaceRequest() {
            return SpaceRequest.builder()
                    .spaceId(spaceId)
                    .memberId(memberId)
                    .build();
        }
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class changeRequestStateParam {
        private String state;
    }

}
