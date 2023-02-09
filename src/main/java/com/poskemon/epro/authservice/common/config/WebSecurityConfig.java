package com.poskemon.epro.authservice.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;

/**
 * 스프링 시큐리티 설정 클래스
 *
 * @author isohyeon
 */
@EnableWebSecurity
@Component
public class WebSecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // http 시큐리티 빌더
        http.cors()
            .and()
            .csrf()
            .disable() // csrf는 현재 사용하지 않으므로 disable
            .httpBasic()
            .disable() // token을 사용하므로 basic 인증 disable
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // session 기반이 아님
            .and()
            .authorizeRequests()
            .antMatchers("/**").permitAll() // 해당 경로는 인증 안해도 됨
            .anyRequest()
            .authenticated();

        return http.build();
    }

    /**
     * 패스워드 암호화
     * 빈 등록을 위해 작성함
     *
     * @return BCryptPasswordEncoder
     */
    @Bean
    public BCryptPasswordEncoder encodePassword() {
        return new BCryptPasswordEncoder();
    }
}