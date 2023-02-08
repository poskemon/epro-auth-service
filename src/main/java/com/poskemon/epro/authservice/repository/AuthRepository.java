package com.poskemon.epro.authservice.repository;

import com.poskemon.epro.authservice.domain.entity.Auth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<Auth, Long> {
    Auth findByEmail(String email);
    Auth findByEmailAndPassword(String email, String password);
}
