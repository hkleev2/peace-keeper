package com.hkleev2.peacekeeper.service.member;

public class DuplicateNicknameException extends IllegalArgumentException {

    public DuplicateNicknameException() {
        super("중복된 닉네임입니다.");
    }
    
}
