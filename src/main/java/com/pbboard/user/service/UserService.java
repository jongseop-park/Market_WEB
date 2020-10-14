package com.pbboard.user.service;

import com.pbboard.user.domain.UserInfo;
import com.pbboard.user.domain.UserInfoDTO;
import com.pbboard.user.mapper.UserMapper;
import lombok.AllArgsConstructor;
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

    @Autowired
    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public UserInfo loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserInfo> userInfo = userMapper.findByMemberId(username);

       /* if(userInfo.isPresent()) {
            System.out.println(userInfo.get().getPassword());
            System.out.println(userInfo.get().getAuthorities());
            System.out.println(userInfo.get().getUsername());
        }*/

        // 아이디 존재하지 않을시 예외
        return userMapper.findByMemberId(username)
                .orElseThrow(() -> new UsernameNotFoundException((username)));
    }

    public String save(UserInfoDTO infoDTO)  {
        // 암호화된 패스워드로 저장
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        infoDTO.setPassword(encoder.encode(infoDTO.getPassword()));

/*
        System.out.println(infoDTO.getUsername());
        System.out.println(infoDTO.getPassword());
        System.out.println(infoDTO.getAuth());
*/
        userMapper.save(infoDTO);

        return "success";
    }
}
