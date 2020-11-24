package com.pbboard.mypage.order.mapper;


import com.pbboard.mypage.order.domain.OrderDetailVO;
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

    /* 주문되어야 하는 금액 조회 */
    public Long selectAmountToBePaid(String orderSeq);

    /* 주문 업데이트 */
    public void updateOrderData(OrderVO orderVO);

    /* 주문 데이터 목록 조회 */
    public List<OrderDetailVO> selectOrderDataList(String merchant_uid);

    /* 재고 조회 */
    public int selectStock(OrderDetailVO orderDetailVO);

    /* 수량 변경 */
    public void changeQuantity(OrderDetailVO orderDetailVO);
}
