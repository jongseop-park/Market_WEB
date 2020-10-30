package com.pbboard.user.service;

import com.pbboard.user.domain.UserInfo;
import com.pbboard.user.domain.UserInfoDTO;
import com.pbboard.user.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Optional;

// UserDetailsService 필수 구현
@Service
public class UserService implements UserDetailsService {
    private final UserMapper userMapper;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public UserInfo loadUserByUsername(String id) {
         return userMapper.findByMemberId(id);
    }

    /* 회원 가입 */
    public String insertUser(UserInfoDTO infoDTO) {
        try {
            // 암호화된 패스워드로 저장
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            infoDTO.setPassword(encoder.encode(infoDTO.getPassword()));
            userMapper.insertUser(infoDTO);
            userMapper.insertIdAuthentication(infoDTO);
            /*userMapper.userAuthority(infoDTO.getId());*/
            return "success";
        } catch(Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }

    //* 로그인 실패 횟수 추가 *//*
    public void countFailure(String username) {
        userMapper.countFailure(username);
    }


    // 실패횟수 확인
    public int checkFailureCount(String username) {
        return userMapper.checkFailureCount(username);
    }

    // 사용불가 계정처리
    public void disabledUsername(String username) {
        userMapper.disabledUsername(username);
    }

    // 로그인 실패횟수 초기화
    public void resetLoginFailureCount(String username) {
        userMapper.resetLoginFailureCount(username);
    }

    // 아이디 중복 조회
    public UserInfo checkId(String id) {
        return userMapper.checkId(id);
    }
}
