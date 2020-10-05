package com.pbboard.Bh.shop.controller;

import com.pbboard.Bh.shop.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ShopController {

    @Autowired
    ShopService shopService;

    @RequestMapping("/BhShop")
    public String shop(Model model){
        model.addAttribute("mainCat",shopService.findMainCat());
        model.addAttribute("subCat",shopService.subCategory());
        model.addAttribute("productColor",shopService.findColor());
        model.addAttribute("productSize",shopService.findSize());

        return "Bh/shop";
    }
}
