package com.pbboard.mypage.order.service;

import com.pbboard.mypage.order.domain.OrderVO;

import java.util.List;

public interface OrderService {

    /* 주문 내역 조회 */
    public List<OrderVO> selectOrderList(String id);
}
