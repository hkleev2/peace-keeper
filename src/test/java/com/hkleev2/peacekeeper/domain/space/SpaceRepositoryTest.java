package com.hkleev2.peacekeeper.domain.space;

import com.hkleev2.peacekeeper.domain.space.model.Space;
import com.hkleev2.peacekeeper.domain.space.model.SpaceMember;
import com.hkleev2.peacekeeper.domain.space.model.Spacename;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class SpaceRepositoryTest {

    @Autowired
    private SpaceRepository spaceRepository;

    @Autowired
    private SpaceMemberRepository spaceMemberRepository;

    private Long adminMemberId = 1L;
    private Long spaceId = 1L;
    private Long memberId = 1L;

    @Test
    void createSpace() {
        String spacename = "spacename";

        Space space = Space.builder()
                .adminMemberId(adminMemberId)
                .spaceKey(UUID.randomUUID().toString())
                .spacename(Spacename.of(spacename))
                .build();

        spaceRepository.save(space);

        assertThat(space.getId()).isNotNull();
        assertThat(space.getSpaceKey()).isNotNull();
    }

    @Test
    void spaceMember() {
        SpaceMember spaceMember = SpaceMember.builder()
                .spaceId(spaceId)
                .memberId(memberId)
                .build();

        spaceMemberRepository.save(spaceMember);

        assertThat(spaceMember.getId()).isNotNull();
    }

    @Test
    void deleteSpaceMember() {
        Long spaceMemberId = 1L;

        spaceMemberRepository.deleteById(spaceMemberId);
        assertThat(spaceMemberRepository.findById(spaceMemberId).isEmpty());
    }

}