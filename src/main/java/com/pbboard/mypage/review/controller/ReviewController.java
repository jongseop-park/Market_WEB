package com.pbboard.mypage.review.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReviewController {

    @GetMapping("/mypage/review")
    public String review() {
        return "/mypage/review/list";
    }

}
