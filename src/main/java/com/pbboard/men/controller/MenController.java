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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            , Model model) throws Exception {
        // 인증 객체 가져오기
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // 만약 비회원이 아니면 아이디 저장, 장바구니 등록 시에 사용
        if(principal != "anonymousUser") {
            String userId = ((UserInfo) principal).getUsername();
            int userSeq = ((UserInfo)principal).getSeq();

            model.addAttribute("id", userId);
            model.addAttribute("userSeq", userSeq);
        }

        try {
            ProductVO productVO = menService.selectProduct(seq);
            List<OptionVO> options = menService.selectOption(seq);
            List<ReviewVO> reviews = menService.selectReviewList(seq);

            model.addAttribute("product", productVO);
            model.addAttribute("options", options);
            model.addAttribute("reviewList", reviews);

            ////

            List<QnaVO> qnaVOS = menService.selectQnaList(seq);
            model.addAttribute("qnaList", qnaVOS);

            // 페이지
            PageMaker pageMaker = new PageMaker();
            pageMaker.setCri(new SearchCriteria());
            pageMaker.setTotalCount(menService.countQna(seq));

            model.addAttribute("pageMaker", pageMaker);

        } catch(Exception e) {
            e.printStackTrace();
            return "redirect:/men/list";
        }

        return "/men/detail";
    }

    @GetMapping("/men/detail/qna_form")
    public String qnaForm() {
        return "/men/qnaForm";
    }

    @ResponseBody
    @PostMapping("/men/cart")
    public String cart(@RequestBody CartDTO cartDTO) {/*
        logger.info(String.valueOf(cartDTO.getUserSeq()));*/
        menService.insertCart(cartDTO);

        return "성공";
    }

    @ResponseBody
    @PostMapping("/men/detail")
    public Map<String, Object> selectQnaList(@RequestBody SearchCriteria searchCriteria) {
        logger.info(String.valueOf(searchCriteria.getPage()));
        List<QnaVO> qnaVOS = menService.selectQnaList2(searchCriteria);

        PageMaker pageMaker = new PageMaker();
        pageMaker.setCri(searchCriteria);
        pageMaker.setTotalCount(menService.countQna(searchCriteria.getProductSeq()));

        Map<String, Object> map = new HashMap<>();
        map.put("list", qnaVOS);
        map.put("page", pageMaker);

        return map;
    }
}
