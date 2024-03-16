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

    private String value;

    private Email(String value) {
        this.value = value;
    }

    public static Email of(String email) {
        return new Email(email);
    }

}
