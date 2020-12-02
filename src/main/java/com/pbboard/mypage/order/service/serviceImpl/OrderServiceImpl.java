package com.pbboard.mypage.order.service.serviceImpl;

import com.pbboard.cart.mapper.CartMapper;
import com.pbboard.mypage.order.domain.OrderDetailVO;
import com.pbboard.mypage.order.domain.OrderVO;
import com.pbboard.mypage.order.mapper.OrderMapper;
import com.pbboard.mypage.order.service.OrderService;
import com.pbboard.user.domain.UserInfo;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderMapper orderMapper;
    private final CartMapper cartMapper;
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    public OrderServiceImpl(OrderMapper orderMapper, CartMapper cartMapper) {
        this.orderMapper = orderMapper;
        this.cartMapper = cartMapper;
    }

    @Override
    public List<OrderVO> selectOrderList(int userSeq) {
        return orderMapper.selectOrderList(userSeq);
    }

    @Override
    public List<OrderVO> selectOrderDetailList(Long orderSeq) {
        return orderMapper.selectOrderDetailList(orderSeq);
    }

    @Override
    public com.pbboard.cart.domain.OrderVO selectOrderDetailInfo(Long orderSeq) {
        return orderMapper.selectOrderDetailInfo(orderSeq);
    }

    /* 결제 유효성 검증 */
    @Transactional
    @Override
    public JSONObject verifyPayment(String imp_uid, String merchant_uid
            , HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        String imp_key 	=	URLEncoder.encode("6265978975330624", "UTF-8");
        String imp_secret =
                URLEncoder.encode("3RN57XPNbuk5fc4WNskS6xk5yNcF3qv6hjcog1csvxyKKCHy52qbALg8zsOCSjJaCVyTWLlsjUd3wUCB"
                        , "UTF-8");

        JSONObject json = new JSONObject();
        json.put("imp_key", imp_key);
        json.put("imp_secret", imp_secret);

        // token 발급 url
        String requestURL = "https://api.iamport.kr/users/getToken";

        // api key, api secret key 값으로 토큰 획득
        String token = getToken(httpServletRequest, httpServletResponse, json, requestURL);

        // imp_uid로 아임포트 서버에서 결제 정보 조회
        JSONObject paymentData =  getPaymentInfo(imp_uid, token);
        JSONObject response = (JSONObject) paymentData.get("response");

        Long amount = (Long) response.get("amount"); // 1. 결제된 금액
        String merchantUid = (String) response.get("merchant_uid"); // 서버 주문번호

        // 2. DB에서 결제되어야 하는 금액 조회
        Long amountToBePaid = orderMapper.selectAmountToBePaid(merchantUid);

        // 서버 주문 상태 update 위해 객체 사용
        OrderVO orderVO = new OrderVO();
        orderVO.setOrderSeq(Long.parseLong(merchantUid));

        // 결제 수단, 결제 일시
        Long payAt = (Long) response.get("paid_at");
        String payMethod = (String) response.get("pay_method");

        orderVO.setPayAt(payAt);
        orderVO.setPayMethod(payMethod);

        // 결제 검증하기(결제된 금액(1)과 결제되어야 하는 금액(2) 일치 여부에 따라)
        if(amount.equals(amountToBePaid)) { // 일치
            switch((String)response.get("status")) {
                //case "ready": break; // 가상계좌 선택 시 사용
                case "paid": // 결제 완료
                    paymentData.put("status", "success");
                    paymentData.put("message", "일반 결제 성공");
                    orderVO.setOrderStatus("결제완료");

                    logger.info(response.toJSONString());
                    logger.info(paymentData.toJSONString());

                    // 결제정보 update
                    //orderMapper.updateOrderData(orderVO);

                    break;
            }
            logger.info("결제 금액 일치");
        } else {
            logger.info("결제 금액 불일치. 위/변조 된 결제");
            paymentData.put("status", "forgery");
            paymentData.put("message", "위조된 결제시도");
            orderVO.setOrderStatus("결제실패(위조된 결제 시도)");
        }

        // 결제정보 update
        orderMapper.updateOrderData(orderVO);

        // 검증 성공 시(금액 유효성, 재고)
        if(paymentData.get("status") == "success") {
            // 장바구니 목록 삭제
            logger.info("order success cart reset..");
            cartMapper.deleteCartList(
                    ((UserInfo)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getSeq());
        } else { // 검증 실패 시
            logger.info("유효성 검증 실패");
            //cancelPay(token, (String)paymentData.get("message"), imp_uid);
        }

        return paymentData;
    }

    // 결제 정보 획득
    public JSONObject getPaymentInfo(String imp_uid, String token) {
        String requestURL = "https://api.iamport.kr/payments/" + imp_uid;

        try {
            // 결제 정보 조회
            String requestString ="";
            URL url = new URL(requestURL);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setDoOutput(true);
            connection.setInstanceFollowRedirects(false);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Authorization", "Bearer " + token);
            OutputStream os = connection.getOutputStream();
            connection.connect();
            StringBuilder sb = new StringBuilder();

            if(connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(connection.getInputStream(), "utf-8"));

                String line = null;
                while((line = br.readLine()) != null) {
                    sb.append(line + "\n");
                }
                br.close();
                requestString = sb.toString();

            }
            os.flush();
            connection.disconnect();

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObj = (JSONObject)jsonParser.parse(requestString);

            if(jsonObj != null) {
                System.out.println("payment Info not null...");
            }

            return jsonObj;

        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* 재고 확인 */
    @Override
    public Boolean selectStock(OrderDetailVO orderDetailVO) {
        Integer stockQuantity;
        stockQuantity = orderMapper.selectStock(orderDetailVO);

        int orderQuantity = orderDetailVO.getQuantity();

        // 재고가 없거나 주문수량보다 재고가 적을 경우
        if(stockQuantity == null || (orderQuantity > stockQuantity)) {
            return false;
        }

        return true;
    }

    @Override
    public void changeQuantity(OrderDetailVO orderDetailVO) {
        orderMapper.changeQuantity(orderDetailVO);
    }


    // 결제 취소
    public JSONObject cancelPay(String access_token, String reason, String imp_uid) {
        String requestURL = "https://api.iamport.kr/payments/cancel";

        JSONObject json = new JSONObject();
        json.put("reason", reason);
        json.put("imp_uid", imp_uid);

        try {
            String requestString ="";
            URL url = new URL(requestURL);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setDoOutput(true);
            connection.setInstanceFollowRedirects(false);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Authorization", access_token);
            OutputStream os = connection.getOutputStream();
            os.write(json.toString().getBytes());
            connection.connect();
            StringBuilder sb = new StringBuilder();

            if(connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(connection.getInputStream(), "utf-8"));

                String line = null;
                while((line = br.readLine()) != null) {
                    sb.append(line + "\n");
                }
                br.close();
                requestString = sb.toString();

            }
            os.flush();
            connection.disconnect();

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObj = (JSONObject)jsonParser.parse(requestString);

            if(jsonObj != null) {
                logger.info("cacel pay json not null..");
                return jsonObj;
            }
        } catch(Exception e) {
            e.printStackTrace();
            logger.info("cancel pay json null..");
        }

        return null;
    }

    // import token
    public String getToken(HttpServletRequest request, HttpServletResponse response
            , JSONObject json, String requestURL) throws Exception {
        String _token = "";

        try {
            String requestString ="";
            URL url = new URL(requestURL);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setDoOutput(true);
            connection.setInstanceFollowRedirects(false);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            OutputStream os = connection.getOutputStream();
            os.write(json.toString().getBytes());
            connection.connect();
            StringBuilder sb = new StringBuilder();

            if(connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(connection.getInputStream(), "utf-8"));

                String line = null;
                while((line = br.readLine()) != null) {
                    sb.append(line + "\n");
                }
                br.close();
                requestString = sb.toString();

            }
            os.flush();
            connection.disconnect();

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObj = (JSONObject)jsonParser.parse(requestString);

            if((Long)jsonObj.get("code") == 0) {
                JSONObject getToken = (JSONObject)jsonObj.get("response");
                System.out.println("getToken==>>"+ getToken.get("access_token"));
                _token = (String)getToken.get("access_token");
            }
        } catch(Exception e) {
            e.printStackTrace();
            _token ="";
        }
        return _token;
    }

    // 최종결제승인 전 재고 확인
    @Override
    public ResponseEntity<JSONObject> paymentConfirm(String merchant_uid) {
        // 재고 확인 후 차감

        // 주문 목록 가져오기 (상품번호, 옵션명, 수량)
        List<OrderDetailVO> orderDetailVOS = orderMapper.selectOrderDataList(merchant_uid);
        JSONObject reason = new JSONObject();

        // for문 재고 확인 존재할경우
        for(OrderDetailVO order : orderDetailVOS) {
            if(selectStock(order)) {
                logger.info("productSeq : " + order.getProductSeq());
                logger.info("재고 존재! 주문 수량 : " + order.getQuantity() + " 차감");

                // 수량 차감
                orderMapper.changeQuantity(order);

                // 재고 존재시 status 변경
            } else {
                logger.info("productSeq : " + order.getProductSeq());
                logger.info("optionName : " + order.getOptionName());
                logger.info("reason : 재고 부족");
                reason.put("reason", "재고 부족");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(reason);
            }
        }

        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
