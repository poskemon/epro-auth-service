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
     * 계정 생성
     *
     * @param auth 생성할 사용자 정보를 담은 객체
     * @return http status와 응답 데이터(등록한 user 또는 에러 메시지)를 반환
     */
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody Auth auth) {
        try {
            if (auth == null) {
                throw new RuntimeException(Message.REQ_DATA_FAIL.getMsg());
            }
            // 저장할 유저 (비밀번호 암호화)
            Auth requestUser = Auth.builder()
                                   .email(auth.getEmail())
                                   .userName(auth.getUserName())
                                   .password(passwordEncoder.encode(auth.getPassword()))
                                   .phoneNumber(auth.getPhoneNumber())
                                   .role(auth.getRole())
                                   .companyName(auth.getCompanyName())
                                   .build();

            Auth registeredUser = authService.create(requestUser);
            registeredUser.setPassword(null); // 비밀번호 숨김

            ResponseDTO response = ResponseDTO.builder()
                                              .msg(Message.SIGNUP_SUCCESS.getMsg())
                                              .data(registeredUser)
                                              .build();
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            ResponseDTO response = ResponseDTO.builder().msg(Message.SIGNUP_FAIL.getMsg()).build();
            return ResponseEntity.badRequest().body(response);
        }
    }

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
                .userName(originalAuth.getUserName())
                .companyName(originalAuth.getCompanyName())
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
