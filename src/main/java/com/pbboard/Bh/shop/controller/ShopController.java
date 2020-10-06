package com.pbboard.Bh.shop.controller;

import com.pbboard.Bh.shop.domain.ShopPageMaker;
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
    public String shop(Model model, ShopPageMaker shopPageMaker){
        shopPageMaker.setTotalCount(shopService.productCount());
        System.out.println(shopPageMaker.makeQuery(1));
        model.addAttribute("mainCat",shopService.findMainCat());    //메인 카테고리
        model.addAttribute("subCat",shopService.subCategory());     //서브 카테고리
        model.addAttribute("productColor",shopService.findColor()); //색상 모음
        model.addAttribute("productSize",shopService.findSize());   //사이즈 모음
        model.addAttribute("productInfo",shopService.findProduct(shopPageMaker));//제품
        model.addAttribute("productDiscount",shopService.findDiscount());//제품 할인 확인
        model.addAttribute("newProduct",shopService.findNewProduct());//신 제품 확인
        model.addAttribute("outOfStock",shopService.findOutOfStock());//품절 확인
        model.addAttribute("pageMaker",shopPageMaker);              //페이징

        return "Bh/shop";
    }
}
