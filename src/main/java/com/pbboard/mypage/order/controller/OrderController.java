package com.pbboard.mypage.order.controller;


import com.pbboard.mypage.order.domain.OrderVO;
import com.pbboard.mypage.order.service.OrderService;
import com.pbboard.user.domain.UserInfo;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

        List<OrderVO> orderVOList = orderService.selectOrderList(userSeq);
        model.addAttribute("orderList", orderVOList);

        return "/mypage/order/list";
    }

    @GetMapping("/mypage/order/detail")
    public String orderDetail(@RequestParam("orderSeq") Long orderSeq, Model model) {
        List<OrderVO> orderVOList = orderService.selectOrderDetailList(orderSeq);
        com.pbboard.cart.domain.OrderVO orderVO = orderService.selectOrderDetailInfo(orderSeq);

        model.addAttribute("orderDetailList", orderVOList);
        model.addAttribute("orderDetailInfo", orderVO);

        return "/mypage/order/detail";
    }

    @ResponseBody
    @PostMapping("/payments/complete")
    public JSONObject paymentComplete(@RequestBody Map<String, Object> data
            , HttpServletRequest request, HttpServletResponse response) throws Exception {
        String imp_uid = (String)data.get("imp_uid");
        String merchant_uid = (String)data.get("merchant_uid");

        logger.info(imp_uid);
        logger.info(merchant_uid);

         return orderService.verifyPayment(imp_uid, merchant_uid, request, response);
    }


    /* 결제 확인 */
    @ResponseBody
    @PostMapping("/payments/confirm")
    public ResponseEntity<JSONObject> paymentConfirm(@RequestBody Map<String, Object> data) {
        logger.info("=========================");
        logger.info("paymentConfirm");
        logger.info("=========================");
        String merchant_uid = (String) data.get("merchant_uid");

        //JSONObject jsonObject = new JSONObject();
        //jsonObject.put("reason", "재고 수량 부족" + new Date().toString());
        //return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(jsonObject);

        return orderService.paymentConfirm(merchant_uid);
    }
}
