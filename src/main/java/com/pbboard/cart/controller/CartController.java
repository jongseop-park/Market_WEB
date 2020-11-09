package com.pbboard.cart.controller;

import com.pbboard.cart.domain.*;
import com.pbboard.cart.service.CartService;
import com.pbboard.user.domain.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

        int userSeq=  userInfo.getSeq();
        model.addAttribute("userSeq", userSeq);
        model.addAttribute("cartList", cartService.selectCartList(userSeq));
        model.addAttribute("cartTotalPrice", cartService.countCartTotalPrice(userSeq));

        return "/cart/list";
    }

    /* 주문 확인 */
    @GetMapping("/cart/checkout")
    public String payment(Model model) {
        int userSeq = ((UserInfo)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getSeq();

        model.addAttribute("orderList", cartService.checkOrderList(userSeq));
        model.addAttribute("cartTotalPrice", cartService.countCartTotalPrice(userSeq));

        return "cart/checkout";
    }

    @PostMapping("/cart/order")
    public String order(OrderVO orderVO ,Model model) throws Exception {
        OrderVO orderResult = cartService.insertOrder(orderVO);
        model.addAttribute("orderResult", orderResult);

        return "/cart/order";
    }

    @GetMapping("/cart/order")
    public String getCartOrder() {
        return "redirect:/login";
    }

    /* 장바구니 삭제 */
    @ResponseBody
    @PostMapping("/cart/delete")
    public String delete(@RequestBody CartDTO cartDTO) throws Exception {
        logger.info(String.valueOf(cartDTO.getSeq()));
        logger.info(String.valueOf(cartDTO.getUserSeq()));

        return cartService.deleteCart(cartDTO);
    }
}
