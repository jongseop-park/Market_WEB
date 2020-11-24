package com.pbboard.cart.service.serviceImpl;


import com.pbboard.cart.domain.*;
import com.pbboard.cart.mapper.CartMapper;
import com.pbboard.cart.service.CartService;
import com.pbboard.cart.domain.OrderDetailVO;
import com.pbboard.user.domain.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.util.Calendar;
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
    public List<CartVO> selectCartList(int userSeq) {
        return cartMapper.selectCartList(userSeq);
    }

    @Override
    public String countCartTotalPrice(int userSeq) {
        return cartMapper.countCartTotalPrice(userSeq);
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
    public List<CartVO> checkOrderList(int userSeq) {
        // 재고 없는 상품 체크

        return cartMapper.checkOrderList(userSeq);
    }

    @Override
    @Transactional
    public OrderVO insertOrder(OrderVO orderVO) {
        // 회원시퀀스, id 조회
        int userSeq = ((UserInfo)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getSeq();
        String id = SecurityContextHolder.getContext().getAuthentication().getName();

        // 넘겨받은 값이 아니라 DB 저장된 값으로 계산하여 저장
        Long totalPrice = cartMapper.selectTotalPrice(userSeq);

        // 만약 총합계금액 존재하지 않을 경우
        if(totalPrice == null)
            throw new RuntimeException();

        // 주문번호 생성
        Long orderSeq = createOrderSeq();

        orderVO.setTotalPrice(String.valueOf(totalPrice));
        orderVO.setUserId(id);
        orderVO.setUserSeq(userSeq);
        orderVO.setSeq(orderSeq);

        // 주문 생성
        cartMapper.insertOrder(orderVO);

        // 장바구니에서  목록 조회
        List<OrderDetailVO> cartList = cartMapper.cartList(userSeq);

        for(OrderDetailVO orderDetailsVO : cartList) {
            //orderDetailsVO.setOrderStatus("ready");
            orderDetailsVO.setOrderSeq(orderSeq);
            
            // 주문 상세 생성
            insertOrderDetails(orderDetailsVO);
        }

        // 주문 결과
        return selectOrderResult(orderVO);
    }

    @Override
    public List<OrderDetailVO> cartList(int userSeq) {
        return cartMapper.cartList(userSeq);
    }

    @Override
    public void changeQuantity(OrderDetailVO orderDetailVO) {
        cartMapper.changeQuantity(orderDetailVO);
    }

    @Override
    public void insertOrderDetails(OrderDetailVO orderDetailVO) {
        cartMapper.insertOrderDetails(orderDetailVO);
    }

    @Override
    public void deleteCartList(int userSeq) {
        cartMapper.deleteCartList(userSeq);
    }

    @Override
    public OrderVO selectOrderResult(OrderVO orderVO) {
        return cartMapper.selectOrderResult(orderVO);
    }

    // 년+월+일+랜덤6자리 주문번호 생성
    private Long createOrderSeq() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        String ym = year + new DecimalFormat("00").format(cal.get(Calendar.MONTH) + 1);
        String ymd = ym + new DecimalFormat("00").format(cal.get(Calendar.DATE));
        String subNum = "";

        for(int i=1; i<=6; i++) {
            subNum += (int)(Math.random() * 10);
        }

        String orderSeq = ymd + subNum;
        Long seq = Long.parseLong(orderSeq);

        return seq;
    }
}
