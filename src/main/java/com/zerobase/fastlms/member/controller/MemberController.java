package com.zerobase.fastlms.member.controller;

import com.zerobase.fastlms.member.entity.Member;
import com.zerobase.fastlms.member.model.MemberInput;
import com.zerobase.fastlms.member.repository.MemberRepository;
import com.zerobase.fastlms.member.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/register")
    public String register() {

        return "member/register";
    }

    @PostMapping("/register")
    public String registerSubmit(Model model,
                                 HttpServletRequest request,
                                 MemberInput memberInput) {

        boolean result = memberService.register(memberInput);
        model.addAttribute("result", result);

        return "member/register_complete";
    }


}
