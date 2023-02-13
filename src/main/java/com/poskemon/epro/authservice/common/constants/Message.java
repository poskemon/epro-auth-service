package com.poskemon.epro.authservice.common.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 응답 메시지 관리
 */
@Getter
@AllArgsConstructor
public enum Message {
    REQ_DATA_FAIL("필수 데이터가 누락되었습니다."),
    ALREADY_USER_FAIL("이미 존재하는 사용자입니다."),
    SIGNUP_FAIL("계정 생성에 실패하였습니다."),
    LOGIN_FAIL("로그인에 실패하였습니다."),

    SIGNUP_SUCCESS("계정이 생성되었습니다."),
    LOGIN_SUCCESS("로그인되었습니다.");

    private String msg;
}
