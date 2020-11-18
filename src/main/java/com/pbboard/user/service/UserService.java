package com.pbboard.user.service;

import com.pbboard.user.domain.UserInfo;
import com.pbboard.user.domain.UserInfoDTO;
import com.pbboard.user.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

// UserDetailsService 필수 구현
@Service
public class UserService implements UserDetailsService {
    private final UserMapper userMapper;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    public static String authenticationKey = createKey();

    @Autowired
    private JavaMailSender mailSender;

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

    public void sendEmail(String recipientEmail) throws UnsupportedEncodingException, MessagingException {
        // 인증키 생성
        authenticationKey = createKey();

        // 보낼 메일 내용
        MimeMessage message1 = mailSender.createMimeMessage();
        message1.addRecipients(Message.RecipientType.TO
                , recipientEmail);
        message1.setSubject("인증번호가 도착했습니다.");

        String html= "";
        html+= "<div style='margin:100px;'>";
        html+= "<h1> 이메일 인증번호가 발급되었습니다. </h1>";
        html+= "<br>";
        html+= "<p>아래 코드를 회원가입 창으로 돌아가 입력해주세요<p>";
        html+= "<br>";
        html+= "<p>감사합니다.<p>";
        html+= "<br>";
        html+= "<div align='center' style='border:1px solid black; font-family:verdana';>";
        html+= "<h3 style='color:blue;'>회원가입 코드입니다.</h3>";
        html+= "<div style='font-size:130%'>";
        html+= "CODE : <strong>";
        html+= authenticationKey + "</strong><div><br/> ";
        html+= "</div>";

        message1.setText(html, "utf-8", "html");
        message1.setFrom(new InternetAddress("seungyongb632@gmail.com", "AD shop"));

        // 인증번호와 함께 메일 전송
        mailSender.send(message1);
    }

    public static String createKey() {
        // 범용고유식별자 클래스 로부터 인증번호 생성 앞의 8자리
        String uuid = UUID.randomUUID().toString();
        String key = uuid.substring(0,8);

        return key;
    }
}
