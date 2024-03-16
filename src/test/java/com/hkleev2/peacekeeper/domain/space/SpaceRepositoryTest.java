package com.hkleev2.peacekeeper.domain.space;

import com.hkleev2.peacekeeper.domain.common.CreatedDateTime;
import com.hkleev2.peacekeeper.domain.member.MemberRepository;
import com.hkleev2.peacekeeper.domain.member.model.Email;
import com.hkleev2.peacekeeper.domain.member.model.Member;
import com.hkleev2.peacekeeper.domain.member.model.Nickname;
import com.hkleev2.peacekeeper.domain.space.model.Space;
import com.hkleev2.peacekeeper.domain.space.model.Spacename;
import com.hkleev2.peacekeeper.fixture.MemberFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class SpaceRepositoryTest {

    @Autowired
    private SpaceRepository spaceRepository;

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

}