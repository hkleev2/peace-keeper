package com.hkleev2.peacekeeper.domain.member.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

public class EmailTest {

    @DisplayName("이메일 객체 생성 성공")
    @ParameterizedTest
    @ValueSource(strings = {"test@test.com"})
    void validEmail(String value) {
        Email.of(value);
    }

    // 유효하지 않은 이메일 예외 케이스
    @DisplayName("유효하지 않은 이메일 예외")
    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"notContainAt", "notContain@Dot", "notContain@DomainTail.", "shortDomain@test.a", "white space@test.com","whitespace@te st.com", "whitespace@test.c om"})
    void invalidEmail(String value) {
        Assertions.assertThatThrownBy(() -> Email.of(value))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
