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

    @Test
    void createSpace() {
        Long adminMemberId = 1L;
        String spaceKey = "123qwe";
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
        Long spaceId = 1L;
        Long memberId = 1L;

        SpaceMember spaceMember = SpaceMember.builder()
                .spaceId(spaceId)
                .memberId(memberId)
                .build();

        spaceMemberRepository.save(spaceMember);

        assertThat(spaceMember.getId()).isNotNull();
    }

}