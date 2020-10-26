package com.pbboard.cart.service;

import com.pbboard.cart.domain.*;
import org.springframework.core.annotation.Order;

import java.util.List;

public interface CartService {

    /* 장바구니 추가 */
    public void save(CartDTO cartDTO);

    /* 장바구니 목록 */
    public List<CartVO> list(String id);

    /* 장바구니 삭제 */
    public String delete(CartDTO cartDTO);

    /* 장바구니 총 합계 금액 */
    public String totalPrice(String id);

    /* 결제 창 이동 */
    public List<CartVO> checkout(String id);

    /* 주문 정보 */
    public void orderInfo(OrderVO orderVO);

    /* 장바구니 비우기 */
    public void cartAllDelete(String id);

    /* 주문 결과 */
    public OrderVO orderConfirm(OrderVO orderVO);

    /* 재고 검사 */
    public Boolean stockCheck(OrderDetailVO orderDetailVO);

    /* 장바구니 목록 */
    public List<OrderDetailVO> cartList(String id);

    /* 수량 변경 */
    public void quantityChange(OrderDetailVO orderDetailVO);

    /* 주문 상세 */
    public void orderInfoDetails(OrderDetailVO orderDetailVO);
}

