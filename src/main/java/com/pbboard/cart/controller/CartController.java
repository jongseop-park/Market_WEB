package com.pbboard.cart.controller;

import com.pbboard.cart.domain.CartVO;
import com.pbboard.cart.service.CartService;
import com.pbboard.user.domain.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


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

        logger.info("username : " + userInfo.getUsername());

        List<CartVO> cartVOList = cartService.list(userId);
        model.addAttribute("list", cartVOList);

        String totalPrice = cartService.totalPrice(userId);
        model.addAttribute("totalPrice", totalPrice);

        return "/cart/list";
    }

    /* 장바구니 삭제 */
    @ResponseBody
    @PostMapping("/cart/delete")
    public String delete(@RequestBody Map<String, Integer> map) throws Exception {
        cartService.delete(map.get("seq"));

        return "성공";
    }


}
