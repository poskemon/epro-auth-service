package com.poskemon.epro.authservice.repository;

import com.poskemon.epro.authservice.domain.entity.Auth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<Auth, Long> {
    Boolean existsByEmail(String email);
    Auth findByEmail(String email);
}
