package com.pbboard.cart.service;

import com.pbboard.cart.domain.*;
import org.springframework.core.annotation.Order;

import java.util.List;

public interface CartService {

    /* 장바구니 목록 */
    public List<CartVO> selectCartList(String id);

    /* 장바구니 삭제 */
    public String deleteCart(CartDTO cartDTO);

    /* 장바구니 총 합계 금액 */
    public String countCartTotalPrice(String id);

    /* 결제 창 이동 */
    public List<CartVO> checkOrderList(String id);

    /* 주문 정보 */
    public void insertOrder(OrderVO orderVO);

    /* 장바구니 비우기 */
    public void deleteCartList(String id);

    /* 주문 결과 */
    public OrderVO selectOrderResult(OrderVO orderVO);

    /* 재고 검사 */
    public Boolean selectStock(OrderDetailVO orderDetailVO);

    /* 장바구니 목록 */
    public List<OrderDetailVO> cartList(String id);

    /* 수량 변경 */
    public void changeQuantity(OrderDetailVO orderDetailVO);

    /* 주문 상세 */
    public void insertOrderDetails(OrderDetailVO orderDetailVO);

    /*--------*/

    public List<CartVO> selectCartList2(int userSeq);
    public String countCartTotalPrice2(int userSeq);
    public String deleteCart2(CartDTO cartDTO);
    public List<CartVO> checkOrderList2(int userSeq);
    public void insertOrder2(OrderVO orderVO);
    public List<OrderDetailVO> cartList2(int userSeq);
    public Boolean selectStock2(OrderDetailVO orderDetailVO);
    public void changeQuantity2(OrderDetailVO orderDetailVO);
    public void insertOrderDetails2(OrderDetailVO orderDetailVO);
    public void deleteCartList2(int userSeq);
    public OrderVO selectOrderResult2(OrderVO orderVO);
}

