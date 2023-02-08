package com.poskemon.epro.authservice.common.constants;

import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 사용자 권한 관리 (사용부서, 바이어, 공급사, 슈퍼바이어)
 */
@AllArgsConstructor
@Getter
public enum UserRole {

    REQUESTER(1), // 사용 부서
    BUYER(2), // 바이어
    VENDOR(3), // 공급사
    SUPER_BUYER(4); // 슈퍼 바이어

    private final int roleCode;

    /**
     * db 데이터를 enum에 등록된 문자열로 변환
     *
     * @param roleCode db에 등록된 코드
     * @return 코드에 매핑된 문자열
     */
    public static UserRole ofRoleCode(int roleCode) {
        return Arrays.stream(UserRole.values())
                     .filter(v -> v.equals(roleCode))
                     .findAny()
                     .orElseThrow(
                         () -> new RuntimeException(String.format("권한 코드에 roleCode=[%s]가 존재하지 않습니다.", roleCode)));
    }
}
