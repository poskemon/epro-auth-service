package com.poskemon.epro.authservice.domain.entity;

import com.poskemon.epro.authservice.common.constants.UserRole;
import com.poskemon.epro.authservice.common.util.UserRoleConverter;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * auth 테이블과 매핑되는 entity 클래스
 */
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "auth", uniqueConstraints = {@UniqueConstraint(columnNames = "email")})
public class Auth {
    @Id
    @Column(name = "user_no")
    private long userNo;
    private String email;
    private String password;
    private UserRole role;
}
