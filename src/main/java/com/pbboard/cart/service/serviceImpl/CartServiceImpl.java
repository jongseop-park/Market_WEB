package com.pbboard.cart.service.serviceImpl;


import com.pbboard.cart.domain.*;
import com.pbboard.cart.mapper.CartMapper;
import com.pbboard.cart.service.CartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    final CartMapper cartMapper;
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    public CartServiceImpl(CartMapper cartMapper) {
        this.cartMapper = cartMapper;
    }

    @Override
    public void save(CartDTO cartDTO) {
        cartMapper.save(cartDTO);
    }

    @Override
    public List<CartVO> list(String id) {
        return cartMapper.list(id);
    }

    @Override
    public String delete(CartDTO cartDTO) {
        try {
            cartMapper.delete(cartDTO);
            return "성공";
        } catch (Exception e) {
            return "실패";
        }
    }

    @Override
    public String totalPrice(String id) {
        return cartMapper.totalPrice(id);
    }

    @Override
    public List<CartVO> checkout(String id) {
        return cartMapper.checkout(id);
    }

 //////

    @Override
    public void orderInfo(OrderVO orderVO) {
        String id = SecurityContextHolder.getContext().getAuthentication().getName();

        if(orderVO.getTotalPrice() == null || orderVO.getTotalPrice() == "")
            throw new RuntimeException();

        orderVO.setTotalPrice(orderVO.getTotalPrice().replaceAll(",", ""));
        orderVO.setUserId(id);

        cartMapper.orderInfo(orderVO);
    }

    @Override
    public void cartAllDelete(String id) {
        cartMapper.cartAllDelete(id);
    }

    @Override
    public OrderVO orderConfirm(OrderVO orderVO) {
        return cartMapper.orderConfirm(orderVO);
    }

    @Override
    public void quantityChange(OrderDetailVO orderDetailVO) {
        cartMapper.quantityChange(orderDetailVO);
    }

    @Override
    public Boolean stockCheck(OrderDetailVO orderDetailVO) {
        Integer stockQuantity;
        stockQuantity = cartMapper.stockCheck(orderDetailVO);

        int orderQuantity = orderDetailVO.getQuantity();

        logger.info("주문 수량 : " + orderDetailVO.getQuantity());
        logger.info("재고 수량 : " + stockQuantity);

        // 재고가 없거나 주문수량보다 재고가 적을 경우
        if(stockQuantity == null || (orderQuantity > stockQuantity)) {
            logger.info("false");
            return false;
        }

        logger.info("true");
        return true;
    }

    @Override
    public List<OrderDetailVO> cartList(String id) {
        return cartMapper.cartList(id);
    }

    @Override
    public void orderInfoDetails(OrderDetailVO orderDetailVO) {
        cartMapper.orderInfoDetails(orderDetailVO);
    }
}
