package com.pbboard.mypage.order.mapper;


import com.pbboard.mypage.order.domain.OrderVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface OrderMapper {
    /* 주문 내역 조회 */
    public List<OrderVO> selectOrderList(String id);
    public List<OrderVO> selectOrderList2(int userSeq);
}
