package com.hkleev2.peacekeeper.controller.common.customException;

public class CheckSpaceAdminException extends Exception {

    public CheckSpaceAdminException() {
        super("공간 관리자만 수정 가능 합니다.");
    }
}
