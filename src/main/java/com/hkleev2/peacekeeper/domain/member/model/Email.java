package com.hkleev2.peacekeeper.domain.member.model;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "value")
@Embeddable
public class Email {

    private static final String EMAIL_REG = "^[A-Za-z0-9+_.-]+@([A-Za-z0-9-]+\\.)+[A-Za-z]{2,}+$";

    private String value;

    private Email(String value) {
        validateNull(value);
        validateEmailPattern(value);

        this.value = value;
    }

    public static Email of(String email) {
        return new Email(email);
    }

    private void validateNull(String value) {
        if (value == null) {
            throw new IllegalArgumentException("이메일에 null을 입력할 수 없습니다.");
        }
    }

    private void validateEmailPattern(String email) {
        if (!email.matches(EMAIL_REG)) {
            throw new IllegalArgumentException("유효하지 않은 이메일 형식입니다.");
        }
    }

}
