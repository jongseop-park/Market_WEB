package com.pbboard.mypage.order.controller;


import com.pbboard.mypage.order.domain.OrderVO;
import com.pbboard.mypage.order.service.OrderService;
import com.pbboard.user.domain.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        int userSeq = ((UserInfo)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getSeq();

/*
        List<OrderVO> orderVOList = orderService.selectOrderList(id);
        model.addAttribute("orderList", orderVOList);
*/
        List<OrderVO> orderVOList = orderService.selectOrderList(userSeq);
        model.addAttribute("orderList", orderVOList);

        return "/mypage/order/list";
    }

    @GetMapping("/mypage/order/detail")
    public String orderDetail(@RequestParam("orderSeq") Long orderSeq, Model model) {
        List<OrderVO> orderVOList = orderService.selectOrderDetailList(orderSeq);
        com.pbboard.cart.domain.OrderVO orderVO = orderService.selectOrderDetailInfo(orderSeq);

    /*    for(OrderVO orderVO1 : orderVOList) {
            logger.info(orderVO1.getProductImage());
            logger.info(orderVO1.getProductBrand());
            logger.info(orderVO1.getProductName());
            logger.info(orderVO1.getOptionName());

            logger.info(orderVO1.getOrderPrice());
            logger.info(String.valueOf(orderVO1.getQuantity()));
            logger.info(orderVO1.getOrderStatus());
        }

        logger.info("===============================================");

        logger.info(orderVO.getAddress());
        logger.info(orderVO.getName());
        logger.info(orderVO.getNote());
        logger.info(orderVO.getPhone());
        logger.info(orderVO.getTotalPrice());
        logger.info(String.valueOf(orderVO.getSeq()));
        logger.info(orderVO.getRegDt());*/

        model.addAttribute("orderDetailList", orderVOList);
        model.addAttribute("orderDetailInfo", orderVO);

        return "/mypage/order/detail";
    }
}
