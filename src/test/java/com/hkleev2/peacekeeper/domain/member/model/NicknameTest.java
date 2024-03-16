package com.hkleev2.peacekeeper.domain.member.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class NicknameTest {

    @DisplayName("닉네임 생성 성공")
    @Test
    void testNicknameOfValid() {
        String validNick = "TestNick";
        Nickname nick = Nickname.of(validNick);
        assertEquals(validNick, nick.getValue());
    }

    @DisplayName("null, 공백, 공백문자 예외")
    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = " ")
    void testNicknameNullOrEmpty(String input) {
        assertThrows(IllegalArgumentException.class, () -> Nickname.of(input));
    }

    @DisplayName("짧은 문자열")
    @Test
    void testNicknameOfShortLength() {
        assertThrows(IllegalArgumentException.class, () -> Nickname.of("a"));
    }

    @DisplayName("긴 문자열")
    @Test
    void testNicknameOfLongLength() {
        assertThrows(IllegalArgumentException.class, () -> Nickname.of("12345678901"));
    }

    @DisplayName("허용하지 않는 문자열")
    @Test
    void testNicknameOfInvalidCharacters() {
        assertThrows(IllegalArgumentException.class, () -> Nickname.of("Nick%$#Name"));
    }

}