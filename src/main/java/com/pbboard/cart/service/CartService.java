package com.pbboard.cart.service;

import com.pbboard.cart.domain.CartDTO;
import com.pbboard.cart.domain.CartVO;

import java.util.List;

public interface CartService {

    /* 장바구니 추가 */
    public void save(CartDTO cartDTO);

    /* 장바구니 목록 */
    public List<CartVO> list();

    /* 장바구니 삭제 */
    public void delete(int seq);

    /* 장바구니 총 합계 금액 */
    public String totalPrice();
}
