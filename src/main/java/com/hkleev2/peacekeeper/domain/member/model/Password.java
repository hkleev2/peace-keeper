package com.hkleev2.peacekeeper.domain.member.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor
@Embeddable
public class Password implements Serializable {

    private String value;

    private Password(String value) {
        if (value == null || value.length() < 6 || value.length() > 20 || value.contains(" ")) {
            throw new IllegalArgumentException();
        }
        this.value = value;
    }

    public static Password of(String password) {
        return new Password(password);
    }

}