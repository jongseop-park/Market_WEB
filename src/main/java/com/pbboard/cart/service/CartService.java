package com.pbboard.cart.service;

import com.pbboard.cart.domain.*;
import com.pbboard.cart.domain.OrderDetailVO;

import java.util.List;

public interface CartService {
    /* 장바구니 목록 조회 */
    public List<CartVO> selectCartList(int userSeq);

    /* 장바구니 총 합계 금액 조회 */
    public String countCartTotalPrice(int userSeq);

    /* 장바구니 삭제 */
    public String deleteCart(CartDTO cartDTO);

    /* 주문 목록 확인 */
    public List<CartVO> checkOrderList(int userSeq);

    /* 주문 생성 */
    public OrderVO insertOrder(OrderVO orderVO);

    /* 장바구니 목록 */
    public List<OrderDetailVO> cartList(int userSeq);

    /* 수량 변경 */
    public void changeQuantity(OrderDetailVO orderDetailVO);

    /* 주문 상세 생성 */
    public void insertOrderDetails(OrderDetailVO orderDetailVO);

    /* 장바구니 목록 삭제 */
    public void deleteCartList(int userSeq);

    /* 주문 결과 조회 */
    public OrderVO selectOrderResult(OrderVO orderVO);
}

