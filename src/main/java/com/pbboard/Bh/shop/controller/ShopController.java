package com.pbboard.Bh.shop.controller;

import com.pbboard.Bh.shop.domain.ShopPageMaker;
import com.pbboard.Bh.shop.service.ShopService;
import com.pbboard.domain.ShopVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ShopController {

    @Autowired
    ShopService shopService;

    @RequestMapping("/BhShop")
    public String shop(Model model, ShopPageMaker shopPageMaker){
        shopPageMaker.setTotalCount(shopService.productCount(shopPageMaker));
        List<ShopVO> productInfo = shopService.findProduct(shopPageMaker);

        model.addAttribute("mainCat",shopService.findMainCat());    //메인 카테고리
        model.addAttribute("subCat",shopService.subCategory());     //서브 카테고리
        model.addAttribute("productColor",shopService.findColor()); //색상 모음
        model.addAttribute("productSize",shopService.findSize());   //사이즈 모음
        model.addAttribute("productInfo",productInfo);              //제품
        model.addAttribute("productDiscount",shopService.findDiscount());//제품 할인 확인
        model.addAttribute("newProduct",shopService.findNewProduct());//신 제품 확인
        model.addAttribute("outOfStock",shopService.findOutOfStock());//품절 확인
        model.addAttribute("pageMaker",shopPageMaker);              //페이징

        return "Bh/shop";
    }

    @RequestMapping("/BhShop/detail")
    public String detail(Model model, ShopVO shopVO){
        Long productSeq = shopVO.getProductSeq();

        model.addAttribute("productInfo",shopService.productDetail(productSeq));    //제품 정보
        model.addAttribute("productReviewNum",shopService.reviewCount(productSeq)); //제품 리뷰 수
        model.addAttribute("reviewStar",shopService.reviewStar(productSeq));    //제품 별점

        return "Bh/product-details";
    }
}
