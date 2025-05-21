package com.zerobase.fastlms.member.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Member {

    @Id
    private String userId;

    private String userName;

    private String password;

    private String phone;

    private LocalDateTime regDt;

    private boolean emailAuthYn;

    private String emailAuthKey;

}
