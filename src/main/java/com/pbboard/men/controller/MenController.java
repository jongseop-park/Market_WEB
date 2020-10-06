package com.pbboard.men.controller;
import com.pbboard.men.domain.*;
import com.pbboard.men.service.MenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MenController {
    MenService menService;

    @Autowired
    public MenController(MenService menService) {
        this.menService = menService;
    }

    /*    @GetMapping("/men")
    public String test(Model model) {
        List<ProductVO> productList = menService.list();
        model.addAttribute("productList", productList);

        return "/men/list";
    }*/

  /*  @GetMapping("/menList")
    public String listPage(@ModelAttribute("cri") Criteria criteria, Model model) {
        List<ProductVO> productVOList = menService.listPage(criteria);
        model.addAttribute("productList", productVOList);

        PageMaker pageMaker = new PageMaker();
        pageMaker.setCri(criteria);
        pageMaker.setTotalCount(menService.listCount());

        model.addAttribute("pageMaker", pageMaker);

        return "/men/list";
    }*/

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

    @GetMapping("/men/detail")
    public String detail(@RequestParam("seq") int seq
            , Model model) {
        ProductVO productVO = menService.detail(seq);
        model.addAttribute("product", productVO);

        List<GoodsVO> goods = menService.goods(seq);
        model.addAttribute("goods", goods);

        return "/men/detail";
    }
}
