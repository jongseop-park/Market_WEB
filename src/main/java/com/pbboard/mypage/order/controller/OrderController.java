package com.pbboard.mypage.order.controller;


import com.pbboard.mypage.order.domain.OrderVO;
import com.pbboard.mypage.order.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class OrderController {
    private final OrderService orderService;
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/mypage/order")
    public String myPage(Model model) {
        String id = SecurityContextHolder.getContext().getAuthentication().getName();

        List<OrderVO> orderVOList = orderService.selectOrderList(id);
        model.addAttribute("orderList", orderVOList);

        return "/mypage/order/list";
    }
}
