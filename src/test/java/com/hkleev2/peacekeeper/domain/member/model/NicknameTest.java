package com.hkleev2.peacekeeper.domain.member.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertThrows;

class NicknameTest {

    @DisplayName("닉네임 객체 생성 성공")
    @ParameterizedTest
    @ValueSource(strings = {"testnick", "한글닉네임"})
    void testNicknameOfValid(String nickname) {
        Nickname.of(nickname);
    }

    @DisplayName("null, 공백, 공백문자 예외")
    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = " ")
    void testNicknameNullOrEmpty(String input) {
        assertThrows(IllegalArgumentException.class, () -> Nickname.of(input));
    }

    @DisplayName("짧은 문자열 예외")
    @Test
    void testNicknameOfShortLength() {
        assertThrows(IllegalArgumentException.class, () -> Nickname.of("a"));
    }

    @DisplayName("긴 문자열 예외")
    @Test
    void testNicknameOfLongLength() {
        assertThrows(IllegalArgumentException.class, () -> Nickname.of("12345678901"));
    }

    @DisplayName("허용하지 않는 문자열 예외")
    @Test
    void testNicknameOfInvalidCharacters() {
        assertThrows(IllegalArgumentException.class, () -> Nickname.of("Nick%$#Name"));
    }



}