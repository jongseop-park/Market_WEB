package com.pbboard.men.controller;
import com.pbboard.men.domain.*;
import com.pbboard.men.service.MenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MenController {
    MenService menService;

    @Autowired
    public MenController(MenService menService) {
        this.menService = menService;
    }

    @GetMapping("/men/list")
    public String listSearch(@ModelAttribute("scri")SearchCriteria searchCriteria,
                             Model model) {
        List<ProductVO> productVOList = menService.menListSearch(searchCriteria);
        model.addAttribute("productList", productVOList);

        PageMaker pageMaker = new PageMaker();
        pageMaker.setCri(searchCriteria);
        pageMaker.setTotalCount(menService.menSearchCount(searchCriteria));

        model.addAttribute("pageMaker", pageMaker);

        return "/men/list";
    }

    @RequestMapping("/men/detail")
    public String detail(@RequestParam("seq") int seq
            , Model model) {
        ProductVO productVO = menService.detail(seq);
        model.addAttribute("product", productVO);

        List<OptionVO> options = menService.option(seq);
        model.addAttribute("option", options);

        List<ReviewVO> reviews = menService.reviewList(seq);
        model.addAttribute("review", reviews);

        return "/men/detail";
    }

  /*  @PostMapping("/men/detail")
    public String registReview(ReviewVO reviewVO) {
        menService.registReview(reviewVO);

        return "redirect:/men/detail?seq=" + reviewVO.getProductSeq();
    }*/
}
