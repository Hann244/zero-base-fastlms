package com.zerobase.fastlms.member.service;

import com.zerobase.fastlms.member.model.MemberInput;

public interface MemberService {

    boolean register(MemberInput memberInput);

    // uuid에 해당하는 계정을 활성화
    boolean emailAuth(String uuid);
}
