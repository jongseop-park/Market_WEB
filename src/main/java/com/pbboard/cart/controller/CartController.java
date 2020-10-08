package com.pbboard.cart.controller;


import com.pbboard.cart.domain.CartDTO;
import com.pbboard.cart.domain.CartVO;
import com.pbboard.cart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;


import java.util.List;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

@Controller
public class CartController {
    @Autowired
    CartService cartService;

    /* 장바구니 목록 */
    @GetMapping("/cart")
    public String cart(Model model) {
        List<CartVO> cartVOList = cartService.list();
        model.addAttribute("list", cartVOList);

        String totalPrice = cartService.totalPrice();
        model.addAttribute("totalPrice", totalPrice);

        return "/cart/list";
    }

    /* 장바구니 삭제 */
    @GetMapping("/delete")
    public String delete(@RequestParam(value = "seq"
            , required = false) int seq) {
        cartService.delete(seq);

        return "redirect:cart";
    }

    /* 장바구니 추가 */
    @ResponseBody
    @PostMapping("save")
    public String save(@RequestBody CartDTO cartDTO) {
        cartService.save(cartDTO);
        return "성공";
    }
}
