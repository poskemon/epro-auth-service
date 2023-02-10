package com.poskemon.epro.authservice.service;

import com.poskemon.epro.authservice.domain.entity.Auth;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface AuthService {
    public Auth create(Auth auth);
    public Auth getByCredentials(final String email, final String password, final PasswordEncoder encoder);
}
