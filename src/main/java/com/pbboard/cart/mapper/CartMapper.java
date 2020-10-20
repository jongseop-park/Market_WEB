package com.pbboard.cart.mapper;


import com.pbboard.cart.domain.CartDTO;
import com.pbboard.cart.domain.CartVO;
import com.pbboard.cart.domain.OrderDetailVO;
import com.pbboard.cart.domain.OrderVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CartMapper {

    /* 장바구니 추가 */
    public void save(CartDTO cartDTO);

    /* 장바구니 목록 */
    public List<CartVO> list(String id);

    /* 장바구니 삭제 */
    public void delete(CartDTO cartDTO);

    /* 장바구니 총 합계 금액 */
    public String totalPrice(String id);

    /* 주문 확인*/
    public List<CartVO> checkout(String id);

    /* 주문 정보 */
    public void orderInfo(OrderVO orderVO);

    /* 주문 상세 정보 */
    public void orderInfoDetails(OrderDetailVO orderDetailVO);

    /* 장바구니 비우기 */
    public void cartAllDelete(String id);

    /* 주문 내역 결과  */
    public OrderVO orderConfirm(OrderVO orderVO);
}
