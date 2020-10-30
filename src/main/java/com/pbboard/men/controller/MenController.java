package com.pbboard.men.controller;
import com.pbboard.men.domain.*;
import com.pbboard.men.service.MenService;
import com.pbboard.user.domain.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MenController {
    MenService menService;
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    public MenController(MenService menService) {
        this.menService = menService;
    }

    @GetMapping("/men/list")
    public String listSearch(@ModelAttribute("scri")SearchCriteria searchCriteria,
                             Model model) {
        List<ProductVO> productVOList = menService.selectProductList(searchCriteria);
        model.addAttribute("productList", productVOList);

        PageMaker pageMaker = new PageMaker();
        pageMaker.setCri(searchCriteria);
        pageMaker.setTotalCount(menService.countProduct(searchCriteria));

        model.addAttribute("pageMaker", pageMaker);

        return "/men/list";
    }

    @RequestMapping("/men/detail")
    public String detail(@RequestParam("seq") int seq
            , Model model) {
        // 인증 객체 가져오기
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // 만약 비회원이 아니면 아이디 저장, 장바구니 등록 시에 사용
        if(principal != "anonymousUser") {
            String userId = ((UserInfo) principal).getUsername();
             model.addAttribute("id", userId);
        }

        ProductVO productVO = menService.selectProduct(seq);
        model.addAttribute("product", productVO);

        List<OptionVO> options = menService.selectOption(seq);
        model.addAttribute("option", options);

        List<ReviewVO> reviews = menService.selectReviewList(seq);

        int sum = 0;
        int averageScore = 0;

        for(ReviewVO reviewVO : reviews) {
            sum += reviewVO.getScore();
        }

        if(reviews.size() > 0)
            averageScore = sum / reviews.size();

        model.addAttribute("averageScore", averageScore);
        model.addAttribute("reviewList", reviews);

        return "/men/detail";
    }

    @ResponseBody
    @PostMapping("/men/cart")
    public String cart(@RequestBody CartDTO cartDTO) {
        menService.insertCart(cartDTO);

        return "성공";
    }
}
