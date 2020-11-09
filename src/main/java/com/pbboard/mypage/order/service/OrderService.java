package com.pbboard.mypage.order.service;

import com.pbboard.mypage.order.domain.OrderVO;

import java.util.List;

public interface OrderService {

    /* 주문 내역 조회 */
    public List<OrderVO> selectOrderList(int userSeq);

    public List<OrderVO> selectOrderDetailList(Long orderSeq);
    public com.pbboard.cart.domain.OrderVO selectOrderDetailInfo(Long orderSeq);
}
