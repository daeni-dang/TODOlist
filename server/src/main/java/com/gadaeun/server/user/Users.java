package com.gadaeun.server.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Users {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull @Column(unique = true)
    private String email;

    @NotNull
    private String pwd;

    @CreatedDate @NotNull
    @Column(updatable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate @NotNull
    private LocalDateTime modifiedDate;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Authority> authorities;

    @Builder
    public Users(String email, String pwd) {
        this.email = email;
        this.pwd = pwd;
        Authority defaultAuthority = new Authority("ROLE_USER");    // 회원가입 시 디폴트로 USER 권한 부여
        authorities = new HashSet<>();
        this.authorities.add(defaultAuthority);
    }

    protected Users() {
    }
}
