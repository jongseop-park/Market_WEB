package com.pbboard.cart.mapper;


import com.pbboard.cart.domain.CartDTO;
import com.pbboard.cart.domain.CartVO;
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
    public void delete(int seq);

    /* 장바구니 총 합계 금액 */
    public String totalPrice(String id);
}
