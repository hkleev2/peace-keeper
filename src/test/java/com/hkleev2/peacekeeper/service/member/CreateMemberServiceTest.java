package com.hkleev2.peacekeeper.service.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


@DisplayName("회원 생성")
@SpringBootTest
class CreateMemberServiceTest {

    @Autowired
    private CreateMemberService createMemberService;

    @Transactional
    @DisplayName("중복된 이메일로는 가입할 수 없다.")
    @Test
    void cannotCreateMemberWithDupEmail() {
        CreateMemberService.CreateMemberParam param = CreateMemberService.CreateMemberParam.builder()
                .email("email@test.com")
                .nickname("nick")
                .build();

        createMemberService.create(param);

        Assertions.assertThatThrownBy(() -> {
                    CreateMemberService.CreateMemberParam dupParam = CreateMemberService.CreateMemberParam.builder()
                            .email("email@test.com")
                            .nickname("노중복")
                            .build();
                    createMemberService.create(dupParam);
                })
                .isInstanceOf(DuplicateEmailException.class)
                .hasMessageContaining("중복된 이메일");
    }

    @Transactional
    @DisplayName("중복된 닉네임으로는 가입할 수 없다.")
    @Test
    void cannotCreateMemberWithDupNickname() {
        CreateMemberService.CreateMemberParam param = CreateMemberService.CreateMemberParam.builder()
                .email("email@test.com")
                .nickname("nick")
                .build();

        createMemberService.create(param);

        Assertions.assertThatThrownBy(() -> {
                    CreateMemberService.CreateMemberParam dupParam = CreateMemberService.CreateMemberParam.builder()
                            .email("notDup@test.com")
                            .nickname("nick")
                            .build();
                    createMemberService.create(dupParam);
                })
                .isInstanceOf(DuplicateNicknameException.class)
                .hasMessageContaining("중복된 닉네임");
    }
}