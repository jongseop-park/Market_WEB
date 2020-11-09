package com.pbboard.mypage.order.service.serviceImpl;

import com.pbboard.mypage.order.domain.OrderVO;
import com.pbboard.mypage.order.mapper.OrderMapper;
import com.pbboard.mypage.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderMapper orderMapper;

    @Autowired
    public OrderServiceImpl(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    @Override
    public List<OrderVO> selectOrderList(int userSeq) {
        return orderMapper.selectOrderList(userSeq);
    }

    @Override
    public List<OrderVO> selectOrderDetailList(Long orderSeq) {
        return orderMapper.selectOrderDetailList(orderSeq);
    }

    @Override
    public com.pbboard.cart.domain.OrderVO selectOrderDetailInfo(Long orderSeq) {
        return orderMapper.selectOrderDetailInfo(orderSeq);
    }
}
