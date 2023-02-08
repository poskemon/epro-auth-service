package com.poskemon.epro.authservice.domain.dto;

import com.poskemon.epro.authservice.common.constants.UserRole;
import com.poskemon.epro.authservice.common.util.UserRoleConverter;
import javax.persistence.Convert;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 로그인 및 인증 응답 객체
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponseDTO {
    private String token;
    private long userNo;
    private String email;
    private UserRole role;
}
