package com.pbboard.user.controller;

import com.pbboard.user.domain.UserInfoDTO;
import com.pbboard.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class UserController {
    private final UserService userService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/")
    public String main() {
        return "index";
    }

    /* 로그인 */
    @GetMapping("/login")
    public String login() {
        logger.info("login");
        return "/user/login";
    }

    /* 회원가입 */
    @PostMapping("/user")
    public String signup(UserInfoDTO infoDTO) throws Exception {
        userService.save(infoDTO);
        return "redirect:/login";
    }

    /* 로그아웃 */
    @GetMapping("/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response,
                SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/index";
    }

    /* 회원가입 페이지 이동 */
    @GetMapping("/signup")
    public String signup() {
        return "/user/registration";
    }

    /* 회원가입 */
    @PostMapping("/saveUser")
    @ResponseBody
    public String signupA(@RequestBody UserInfoDTO userInfoDTO) {
        return userService.save(userInfoDTO);
    }
}
