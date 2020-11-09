package com.pbboard.mypage.order.mapper;


import com.pbboard.mypage.order.domain.OrderVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface OrderMapper {
    /* 주문 내역 조회 */
    public List<OrderVO> selectOrderList(int userSeq);

    /* 주문 상세 리스트 조회 */
    public List<OrderVO> selectOrderDetailList(Long orderSeq);

    /* 주문 상세 정보 조회 */
    public com.pbboard.cart.domain.OrderVO selectOrderDetailInfo(Long orderSeq);
}
