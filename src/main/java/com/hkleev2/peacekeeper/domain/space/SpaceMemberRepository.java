package com.hkleev2.peacekeeper.domain.space;

import com.hkleev2.peacekeeper.domain.space.model.SpaceMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpaceMemberRepository extends JpaRepository<SpaceMember, Long> {

}
