package com.pbboard.user.service;

import com.pbboard.user.domain.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;


public class CustomAuthenticationProvider implements AuthenticationProvider {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    public CustomAuthenticationProvider(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 사용자가 입력한 정보
        String username = (String) authentication.getName();
        String password = (String) authentication.getCredentials();

        // DB에서 가져온 정보
        UserInfo userInfo = (UserInfo) userService.loadUserByUsername(username);

        // 인증 진행
        // DB에 정보가 존재하지 않을 때
        // 아이디 또는 비밀번호가 일치하지 않을 때
        if (userInfo == null) {
            throw new AuthenticationServiceException(null);
        } else if(!username.equals(userInfo.getUsername())
                || !passwordEncoder.matches(password, userInfo.getPassword())) {
            throw new BadCredentialsException(username);
        } else if(!userInfo.isEnabled()) { // 비활성화 된 계정일 경우
            throw new DisabledException(username);
        } else if(!userInfo.isAccountNonLocked()) { // 잠긴 계정일 경우
            throw new LockedException(username);
        } else if(!userInfo.isAccountNonExpired()) { // 만료된 계정일 경우
            throw new AccountExpiredException(username);
        } else if(!userInfo.isCredentialsNonExpired()) { // 비밀번호가 만료된 경우
            throw new CredentialsExpiredException(username);
        }

        // 권한 부여된 객체 리턴
        return new UsernamePasswordAuthenticationToken(userInfo, null, userInfo.getAuthorities());
    }

    // authenticate 메소드에서 반환한 객체가 유효한 타입이 맞는지 검사
    // null 값 또는 잘못된 타입을 반환했을 경우 인증 실패로 간주
    @Override
    public boolean supports(Class<?> aClass) {
        // 스프링 시큐리티가 요구하는 UsernamePasswordAuthenticationToken 타입이 맞는지 검사
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
