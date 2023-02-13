package com.poskemon.epro.authservice.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
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
@SequenceGenerator(
    name = "user_seq_generator",
    sequenceName = "user_seq", // 매핑할 데이터베이스 시퀀스 이름
    initialValue = 1000, allocationSize = 1)
public class Auth {
    @Id
    @Column(name = "user_no")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
                    generator = "user_seq_generator")
    private long userNo;
    private String email;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "phone_number")
    private String phoneNumber;
    private int role;
    @Column(name = "company_name")
    private String companyName;
    private String password;
}
