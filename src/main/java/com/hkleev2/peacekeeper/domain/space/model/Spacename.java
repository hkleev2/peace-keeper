package com.hkleev2.peacekeeper.domain.space.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@Embeddable
@NoArgsConstructor
public class Spacename implements Serializable {

    private String value;

    private Spacename(String value) {
        validateNull(value);
        validateLength(value);
        validateCharacter(value);
        this.value = value;
    }

    private void validateNull(String value) {
        if (value == null) {
            throw new IllegalArgumentException("공간명에 null을 입력할 수 없습니다.");
        }
    }

    private void validateLength(String value) {
        if (value.isBlank() || value.length() < 1 || value.length() > 10) {
            throw new IllegalArgumentException("공간명임 길이는 1자에서 10자 사이여야 합니다");
        }
    }

    private void validateCharacter(String value) {
        if (!value.matches("[a-zA-Z0-9_-]+")) {
            throw new IllegalArgumentException("공간명에 사용할 수 없는 문자가 포함되어 있습니다. 알파벳, 숫자, 밑줄(_), 하이픈(-)만 사용할 수 있습니다.");
        }
    }

    public static Spacename of(String spacename) {
        return new Spacename(spacename);
    }

}