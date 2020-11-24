package com.pbboard.mypage.order.service;

import com.pbboard.mypage.order.domain.OrderDetailVO;
import com.pbboard.mypage.order.domain.OrderVO;
import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.List;

public interface OrderService {

    /* 주문 내역 조회 */
    public List<OrderVO> selectOrderList(int userSeq);

    public List<OrderVO> selectOrderDetailList(Long orderSeq);
    public com.pbboard.cart.domain.OrderVO selectOrderDetailInfo(Long orderSeq);

    public JSONObject verifyPayment(String imp_uid, String merchant_uid
            , HttpServletRequest request, HttpServletResponse response) throws Exception;

    public Boolean selectStock(OrderDetailVO orderDetailVO);
    public void changeQuantity(OrderDetailVO orderDetailVO);

    public ResponseEntity<JSONObject> paymentConfirm(String merchant_uid);
}
