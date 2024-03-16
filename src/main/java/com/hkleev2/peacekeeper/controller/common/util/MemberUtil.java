package com.hkleev2.peacekeeper.controller.common.util;

import com.hkleev2.peacekeeper.controller.common.customException.CheckLoginException;
import jakarta.servlet.http.HttpSession;

public class MemberUtil {

    public static void chkLogin(HttpSession session) throws CheckLoginException {

        if (isLogin(session)) {
            throw new CheckLoginException("로그인 후 이용 가능 합니다.");
        }
    }

    public static boolean isLogin(HttpSession session) {

        if (session == null) {
            return false;
        }
        return true;
    }

    public static Long getLoginId(HttpSession session) {
        return Long.parseLong(String.valueOf(session.getAttribute("loginId")));
    }
}