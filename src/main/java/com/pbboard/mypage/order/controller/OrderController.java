package com.pbboard.mypage.order.controller;


import com.pbboard.mypage.order.domain.OrderVO;
import com.pbboard.mypage.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/mypage/order")
    public String myPage(Model model) {
        String id = SecurityContextHolder.getContext().getAuthentication().getName();

        List<OrderVO> orderVOList = orderService.orderList(id);
        model.addAttribute("orderList", orderVOList);

        return "/mypage/order/list";
    }
}
