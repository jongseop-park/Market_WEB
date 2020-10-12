package com.pbboard.cart.controller;

import com.pbboard.cart.domain.CartVO;
import com.pbboard.cart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


@Controller
public class CartController {
    final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    /* 장바구니 목록 */
    @GetMapping("/cart/list")
    public String cart(Model model) {
        List<CartVO> cartVOList = cartService.list();
        model.addAttribute("list", cartVOList);

        String totalPrice = cartService.totalPrice();
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
