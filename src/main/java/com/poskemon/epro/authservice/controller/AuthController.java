package com.poskemon.epro.authservice.controller;

import com.poskemon.epro.authservice.common.constants.Message;
import com.poskemon.epro.authservice.common.util.TokenProvider;
import com.poskemon.epro.authservice.domain.dto.AuthResponseDTO;
import com.poskemon.epro.authservice.domain.dto.ResponseDTO;
import com.poskemon.epro.authservice.domain.entity.Auth;
import com.poskemon.epro.authservice.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AuthController {

    private final AuthService authService;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    /**
     * 로그인
     *
     * 로그인 성공 시 토큰 생성 후 반환
     * @param auth 로그인 정보를 담은 객체
     * @return http status와 응답 데이터(사용자 정보, 메시지)를 반환
     */
    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody Auth auth) {
        Auth originalAuth = authService.getByCredentials(auth.getEmail(), auth.getPassword(), passwordEncoder);

        if(originalAuth != null) {
            // 토큰 생성
            final String token = tokenProvider.create(originalAuth);
            final AuthResponseDTO authResponseDTO = AuthResponseDTO.builder()
                .token(token)
                .userNo(originalAuth.getUserNo())
                .email(originalAuth.getEmail())
                .role(originalAuth.getRole())
                .build();

            ResponseDTO responseDTO = ResponseDTO.builder().msg(Message.LOGIN_SUCCESS.getMsg()).data(authResponseDTO).build();
            return ResponseEntity.ok().body(responseDTO);
        } else {
            ResponseDTO responseDTO = ResponseDTO.builder().msg(Message.LOGIN_FAIL.getMsg()).build();
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }
}
