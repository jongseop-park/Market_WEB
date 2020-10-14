package com.pbboard;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncodeTest {
    public static void main(String args[]) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodeTest = passwordEncoder.encode("1234");
        String encodeTest2 = passwordEncoder.encode("456");

        System.out.println(encodeTest);
        System.out.println(encodeTest2);
    }
}
