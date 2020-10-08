package com.pbboard.mypage.order.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderController {

    @GetMapping("/mypage/order")
    public String myPage() {
        return "/mypage/order/list";
    }
}
