package com.hkleev2.peacekeeper.service.member;

public class DuplicateEmailException extends IllegalArgumentException {

    public DuplicateEmailException() {
        super("중복된 이메일입니다.");
    }

}
