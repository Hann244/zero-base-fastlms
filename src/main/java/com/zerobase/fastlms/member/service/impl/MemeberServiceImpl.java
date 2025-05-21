package com.zerobase.fastlms.member.service.impl;

import com.zerobase.fastlms.components.MailComponents;
import com.zerobase.fastlms.member.entity.Member;
import com.zerobase.fastlms.member.model.MemberInput;
import com.zerobase.fastlms.member.repository.MemberRepository;
import com.zerobase.fastlms.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemeberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final MailComponents mailComponents;

    // 회원가입
    @Override
    public boolean register(MemberInput memberInput) {

        Optional<Member> optionalMember = memberRepository.findById(memberInput.getUserId());
        if (optionalMember.isPresent()) {
            // 현재 userId에 해당하는 데이터 존재
            return false;
        }

        String uuid = UUID.randomUUID().toString();

        Member member = new Member();
        member.setUserId(memberInput.getUserId());
        member.setUserName(memberInput.getUserName());
        member.setPassword(memberInput.getPassword());
        member.setPhone(memberInput.getPhone());
        member.setRegDt(LocalDateTime.now());

        member.setEmailAuthYn(false);
        member.setEmailAuthKey(uuid);

        memberRepository.save(member);

        String email = memberInput.getUserId();
        String subject = "fastlms 사이트 가입을 축하드립니다.";
        String text = "<p>fastlms 사이트 가입을 축하드립니다.</p>" +
                "<p>아래 링크를 클릭하셔서 가입을 완료하세요.</p>" +
                "<div><a href='http://localhost:8080/member/email-auth?id=" + uuid + "'> 가입 완료 </a></div>";
        mailComponents.sendMail(email, subject, text);

        return true;
    }
}
