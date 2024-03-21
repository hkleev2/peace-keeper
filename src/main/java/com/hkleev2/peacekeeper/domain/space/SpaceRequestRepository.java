package com.hkleev2.peacekeeper.domain.space;

import com.hkleev2.peacekeeper.domain.space.model.SpaceMember;
import com.hkleev2.peacekeeper.domain.space.model.SpaceRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpaceRequestRepository extends JpaRepository<SpaceRequest, Long> {

}
