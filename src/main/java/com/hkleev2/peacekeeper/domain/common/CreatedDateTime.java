package com.hkleev2.peacekeeper.domain.common;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Embeddable
public class CreatedDateTime implements Serializable {

    private LocalDateTime value;

    private CreatedDateTime(LocalDateTime value) {
        this.value = value;
    }

    public static CreatedDateTime of(LocalDateTime value) {
        return new CreatedDateTime(value);
    }

}
