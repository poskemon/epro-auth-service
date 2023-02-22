package com.poskemon.epro.authservice.service.impl;

import com.poskemon.epro.authservice.common.constants.Message;
import com.poskemon.epro.authservice.domain.dto.UserDTO;
import com.poskemon.epro.authservice.domain.entity.Auth;
import com.poskemon.epro.authservice.repository.AuthRepository;
import com.poskemon.epro.authservice.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final AuthRepository authRepository;
    private final KafkaTemplate<String, UserDTO> kafkaTemplate;

    /**
     * 계정 생성
     *
     * @param auth 등록할 사용자 정보를 다음 객체
     * @return the saved entity
     */
    @Override
    public Auth create(Auth auth) {
        if(auth == null) {
            throw new RuntimeException(Message.REQ_DATA_FAIL.getMsg());
        }
        final String email = auth.getEmail();
        if(authRepository.existsByEmail(email)) {
            throw new RuntimeException(Message.ALREADY_USER_FAIL.getMsg());
        }
        Auth newAuth = authRepository.save(auth);
        kafkaTemplate.send("user-register", new UserDTO(newAuth.getUserNo(),
                                                        newAuth.getEmail(),
                                                        newAuth.getUserName(),
                                                        newAuth.getPhoneNumber(),
                                                        newAuth.getRole(),
                                                        newAuth.getCompanyName()));

        return newAuth;
    }

    /**
     * 로그인 인증
     *
     * @param email    로그인 대상의 email
     * @param password 로그인 대상의 password
     * @param encoder  스프링 시큐리티가 제공하는 BcryptPasswordEncoder
     * @return 로그인 유저 정보를 담은 Auth 엔티티 또는 null
     */
    @Override
    public Auth getByCredentials(String email, String password, PasswordEncoder encoder) {
        final Auth originalAuth = authRepository.findByEmail(email);

        // matches 메서드로 패스워드 확인
        if (originalAuth != null && encoder.matches(password, originalAuth.getPassword())) {
            return originalAuth;
        }
        return null;
    }
}
