package com.pbboard.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Service;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

@Service
public class UserLoginSuccessHandler implements AuthenticationSuccessHandler {
    final UserService userService;

    @Autowired
    public UserLoginSuccessHandler(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request
            , HttpServletResponse response
            , Authentication authentication) throws IOException, ServletException {
        // 로그인 실패 횟수 초기화
        userService.resetLoginFailureCount(authentication.getName());

         // 디폴트 URI
        String uri = "/";

        /* 강제 인터셉트 당했을 경우의 데이터 가져오기 */
        RequestCache requestCache = new HttpSessionRequestCache();
        SavedRequest savedRequest = requestCache.getRequest(request, response);

        /* 로그인 버튼 눌러 접속 했을 경우의 데이터 가져오기 */
        String prevPage = (String)request.getSession().getAttribute("prePage");

        if(prevPage != null) {
            // 세션 정보 사용후 제거
            request.getSession().removeAttribute("prePage");
        }

        if(savedRequest != null) {  // 강제 인터셉트 당했을 경우
            uri = savedRequest.getRedirectUrl();
            // 세션에 저장된 객체를 다 사용한 후 메모리 누수 방지를 위해 제거
            requestCache.removeRequest(request, response);

        } else if(prevPage != null && !prevPage.equals("")) { // 로그인 버튼을 눌러 로그인했을 때
            uri = prevPage;
        }

        // 세 가지 케이스에 따른 URI 주소로 리다이렉트
        response.sendRedirect(uri);
    }
}
