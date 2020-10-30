package com.pbboard.cart.mapper;


import com.pbboard.cart.domain.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CartMapper {
    /* 장바구니 목록 */
    public List<CartVO> selectCartList(String id);

    /* 장바구니 삭제 */
    public void deleteCart(CartDTO cartDTO);

    /* 장바구니 총 합계 금액 */
    public String countCartTotalPrice(String id);

    /* 주문 확인*/
    public List<CartVO> checkOrderList(String id);

    /* 주문 정보 */
    public void insertOrder(OrderVO orderVO);

    /* 장바구니 비우기 */
    public void deleteCartList(String id);

    /* 주문 내역 결과  */
    public OrderVO selectOrderResult(OrderVO orderVO);

    /* 수량 변경*/
    public void changeQuantity(OrderDetailVO orderDetailVO);

    /* 재고 목록 */
    public Integer selectStock(OrderDetailVO orderDetailVO);

    /* 장바구니 목록 */
    public List<OrderDetailVO> cartList(String id);

    /* 주문 상세 정보 */
    public void insertOrderDetails(OrderDetailVO orderDetailVO);
}
