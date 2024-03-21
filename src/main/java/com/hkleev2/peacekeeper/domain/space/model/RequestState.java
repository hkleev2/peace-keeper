package com.hkleev2.peacekeeper.domain.space.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@Embeddable
@NoArgsConstructor
public class RequestState implements Serializable {

    private String value;

    private RequestState(String value) {
        setState(value);
        this.value = value;
    }

    private void setState(String value) {
        if (value == null) {
            throw new IllegalArgumentException("공간명에 null을 입력할 수 없습니다.");
        }
    }

    public static RequestState of(String state) {
        return new RequestState(state);
    }

}