package com.hkleev2.peacekeeper.domain.member.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@Embeddable
@NoArgsConstructor
public class Nickname implements Serializable {

    private String value;

    private Nickname(String value) {
        validateNull(value);
        validateLength(value);
        validateCharacter(value);
        this.value = value;
    }

    private void validateNull(String value) {
        if (value == null) {
            throw new IllegalArgumentException("닉네임에 null을 입력할 수 없습니다.");
        }
    }

    private void validateLength(String value) {
        if (value.isBlank() || value.length() < 2 || value.length() > 10) {
            throw new IllegalArgumentException("닉네임 길이는 2자에서 10자 사이여야 합니다");
        }
    }

    private void validateCharacter(String value) {
        if (!value.matches("[a-zA-Z0-9_-]+")) {
            throw new IllegalArgumentException("닉네임에 사용할 수 없는 문자가 포함되어 있습니다. 알파벳, 숫자, 밑줄(_), 하이픈(-)만 사용할 수 있습니다.");
        }
    }

    public static Nickname of(String nickname) {
        return new Nickname(nickname);
    }

}