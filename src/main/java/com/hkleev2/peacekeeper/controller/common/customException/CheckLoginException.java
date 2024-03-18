package com.hkleev2.peacekeeper.controller.common.customException;

public class CheckLoginException extends Exception {

    public CheckLoginException() {
        super("로그인 후 이용 가능 합니다.");
    }
}
