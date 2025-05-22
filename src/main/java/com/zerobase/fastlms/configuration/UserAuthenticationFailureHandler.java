package com.zerobase.fastlms.configuration;

import com.zerobase.fastlms.member.exception.MemberNotEmailAuthException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import java.io.IOException;

public class UserAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        String msg = "로그인에 실패하였습니다.";

        if (exception instanceof InternalAuthenticationServiceException) {
            // 내부 예외의 원인을 확인
            Throwable cause = exception.getCause();
            if (cause instanceof MemberNotEmailAuthException) {
                msg = cause.getMessage();
            } else if (cause instanceof UsernameNotFoundException) {
                msg = cause.getMessage();
            } else {
                msg = exception.getMessage();
            }
        } else if (exception instanceof BadCredentialsException) {
            msg = "아이디 또는 비밀번호가 일치하지 않습니다.";
        }
        setDefaultFailureUrl("/member/login?error=true");
        request.getSession().setAttribute("errorMessage", msg);
        request.setAttribute("errorMessage", msg);

        super.onAuthenticationFailure(request, response, exception);
    }
}
