package com.poskemon.epro.authservice.common.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 응답 메시지 관리
 */
@Getter
@AllArgsConstructor
public enum Message {
    LOGIN_FAIL("로그인에 실패하였습니다."),
    LOGIN_SUCCESS("로그인되었습니다.");

    private String msg;
}
