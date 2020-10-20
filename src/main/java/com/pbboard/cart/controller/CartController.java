package com.pbboard.cart.controller;

import com.pbboard.cart.domain.CartDTO;
import com.pbboard.cart.domain.CartVO;
import com.pbboard.cart.domain.OrderDetailVO;
import com.pbboard.cart.domain.OrderVO;
import com.pbboard.cart.service.CartService;
import com.pbboard.user.domain.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.List;

@Controller
public class CartController {
    final CartService cartService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    /* 장바구니 목록 */
    @GetMapping("/cart/list")
    public String cart(Model model) {
        // 로그인 객체에서 id 가져옴
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserInfo userInfo = (UserInfo)principal;
        String userId = userInfo.getUsername();

        model.addAttribute("id", userId);

        List<CartVO> cartVOList = cartService.list(userId);
        model.addAttribute("list", cartVOList);

        String totalPrice = cartService.totalPrice(userId);
        model.addAttribute("totalPrice", totalPrice);

        return "/cart/list";
    }

    /* 장바구니 삭제 */
    @ResponseBody
    @PostMapping("/cart/delete")
    public String delete(@RequestBody CartDTO cartDTO) throws Exception {
        return cartService.delete(cartDTO);
    }

    /* 주문 확인 */
    @GetMapping("/cart/checkout")
    public String payment(Model model) {
        String id = SecurityContextHolder.getContext().getAuthentication().getName();

        List<CartVO> cartVOList = cartService.checkout(id);
        model.addAttribute("cartVOList", cartVOList);

        String totalPrice = cartService.totalPrice(id);
        model.addAttribute("totalPrice", totalPrice);

        return "cart/checkout";
    }

    @PostMapping("/cart/order")
    public String order(OrderVO orderVO, OrderDetailVO orderDetailVO
                        ,Model model) {
        String id = SecurityContextHolder.getContext().getAuthentication().getName();
        orderVO.setUserId(id);

        // 주문번호 생성
        final Long orderSeq = createOrderSeq();

        // 콤마(,) 제거
        orderVO.setTotalPrice(orderVO.getTotalPrice().replaceAll(",", ""));
        orderVO.setSeq(orderSeq);

        // 주문 등록
        cartService.orderInfo(orderVO);

        orderDetailVO.setUserId(id);
        orderDetailVO.setOrderSeq(orderSeq);

        // 주문 상세 등록
        cartService.orderInfoDetails(orderDetailVO);

        // 주문 후 장바구니 목록 삭제
        cartService.cartAllDelete(id);

        // 주문 결과
        OrderVO vo = cartService.orderConfirm(orderVO);
        model.addAttribute("vo", vo);

        return "/cart/order";
    }

    // 년+월+일+랜덤6자리 주문번호 생성
    private Long createOrderSeq() {

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        String ym = year + new DecimalFormat("00").format(cal.get(Calendar.MONTH) + 1);
        String ymd = ym + new DecimalFormat("00").format(cal.get(Calendar.DATE));
        String subNum = "";

        for(int i=1; i<=6; i++) {
            subNum += (int)(Math.random() * 10);
        }

        String orderSeq = ymd + subNum;
        Long seq = Long.parseLong(orderSeq);

        return seq;
    }
}
