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
    public UserInfo loadUserByUsername(String id) throws UsernameNotFoundException{
        // 아이디 존재하지 않을시 예외
        return userMapper.findByMemberId(id)
                .orElseThrow(() -> new UsernameNotFoundException((id)));
    }

    public String save(UserInfoDTO infoDTO) {
        try {
            // 암호화된 패스워드로 저장
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            infoDTO.setPassword(encoder.encode(infoDTO.getPassword()));

            userMapper.save(infoDTO);
            return "success";
        } catch(Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }
}
