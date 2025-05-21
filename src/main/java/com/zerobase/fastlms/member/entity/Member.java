package com.zerobase.fastlms.member.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id
    private String userId;

    private String userName;

    private String password;

    private String phone;

    private LocalDateTime regDt;

    private boolean emailAuthYn;

    private LocalDateTime emailAuthDt;

    private String emailAuthKey;

}
