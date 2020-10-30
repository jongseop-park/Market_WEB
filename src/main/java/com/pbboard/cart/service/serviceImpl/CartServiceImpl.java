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
    public List<CartVO> selectCartList(String id) {
        return cartMapper.selectCartList(id);
    }

    @Override
    public String deleteCart(CartDTO cartDTO) {
        try {
            cartMapper.deleteCart(cartDTO);
            return "성공";
        } catch (Exception e) {
            return "실패";
        }
    }

    @Override
    public String countCartTotalPrice(String id) {
        return cartMapper.countCartTotalPrice(id);
    }

    @Override
    public List<CartVO> checkOrderList(String id) {
        return cartMapper.checkOrderList(id);
    }

 //////

    @Override
    public void insertOrder(OrderVO orderVO) {
        String id = SecurityContextHolder.getContext().getAuthentication().getName();

        if(orderVO.getTotalPrice() == null || orderVO.getTotalPrice() == "")
            throw new RuntimeException();

        orderVO.setTotalPrice(orderVO.getTotalPrice().replaceAll(",", ""));
        orderVO.setUserId(id);

        cartMapper.insertOrder(orderVO);
    }

    @Override
    public void deleteCartList(String id) {
        cartMapper.deleteCartList(id);
    }

    @Override
    public OrderVO selectOrderResult(OrderVO orderVO) {
        return cartMapper.selectOrderResult(orderVO);
    }

    @Override
    public void changeQuantity(OrderDetailVO orderDetailVO) {
        cartMapper.changeQuantity(orderDetailVO);
    }

    @Override
    public Boolean selectStock(OrderDetailVO orderDetailVO) {
        Integer stockQuantity;
        stockQuantity = cartMapper.selectStock(orderDetailVO);

        int orderQuantity = orderDetailVO.getQuantity();

       /* logger.info("주문 수량 : " + orderDetailVO.getQuantity());
        logger.info("재고 수량 : " + stockQuantity);*/

        // 재고가 없거나 주문수량보다 재고가 적을 경우
        if(stockQuantity == null || (orderQuantity > stockQuantity)) {
         /*   logger.info("false");
         */   return false;
        }

        /*logger.info("true");
        */return true;
    }

    @Override
    public List<OrderDetailVO> cartList(String id) {
        return cartMapper.cartList(id);
    }

    @Override
    public void insertOrderDetails(OrderDetailVO orderDetailVO) {
        cartMapper.insertOrderDetails(orderDetailVO);
    }
}
