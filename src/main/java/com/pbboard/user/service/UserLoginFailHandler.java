package com.pbboard.user.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserLoginFailHandler implements AuthenticationFailureHandler{
     Logger logger = LoggerFactory.getLogger(this.getClass());

     @Autowired
     UserService userService;

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest
            , HttpServletResponse httpServletResponse
            , AuthenticationException e) throws IOException, ServletException {

        // 에러 종류에 맞게 실패 메시지 저장
        if(e instanceof AuthenticationServiceException) {
            httpServletRequest.setAttribute("loginFailMsg"
                    , "존재하지 않는 사용자 입니다.");
        } else if(e instanceof BadCredentialsException) {
            // 실패횟수 count 로직 작성
            String username = httpServletRequest.getParameter("username");
            loginFailureCount(username);

            httpServletRequest.setAttribute("loginFailMsg"
                    , "아이디 또는 비밀번호가 틀립니다.");
        } else if(e instanceof LockedException) {
            httpServletRequest.setAttribute("loginFailMsg"
                    , "잠긴 계정입니다.");
        } else if(e instanceof DisabledException) {
            httpServletRequest.setAttribute("loginFailMsg"
                    , "비활성화 된 계정입니다.");
        } else if(e instanceof AccountExpiredException) {
            httpServletRequest.setAttribute("loginFailMsg"
                    , "만료된 계정입니다.");
        } else if(e instanceof CredentialsExpiredException) {
            httpServletRequest.setAttribute("loginFailMsg"
                    , "비밀번호가 만료되었습니다.");
        } else if(e instanceof UsernameNotFoundException) {
            httpServletRequest.setAttribute("loginFailMsg"
            , "존재하지 않는 아이디 입니다.");
        }

        // 로그인 페이지로 다시 포워딩
        RequestDispatcher dispatcher =
                httpServletRequest.getRequestDispatcher("/login");
        dispatcher.forward(httpServletRequest, httpServletResponse);
    }

    public void loginFailureCount(String username) {
        userService.countFailure(username);
        int cnt = userService.checkFailureCount(username);


        /* 실패횟수 3회 이상일 경우 */
        if(cnt >= 3) {
            userService.disabledUsername(username);
            // 잠금처리
        }
    }
}
