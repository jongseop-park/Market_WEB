package com.pbboard.mypage.review.controller;
import com.pbboard.mypage.review.domain.ReviewDTO;
import com.pbboard.mypage.review.domain.ReviewVO;
import com.pbboard.mypage.review.service.ReviewService;
import com.pbboard.user.domain.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ReviewController {
    ReviewService reviewService;
    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    /* 구매후기 목록 */
    @GetMapping("/mypage/review")
    public String review(Model model) {
        String id = SecurityContextHolder.getContext().getAuthentication().getName();
        int userSeq = ((UserInfo)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getSeq();
        List<ReviewVO> reviewVOS = reviewService.selectReviewList2(userSeq);

        /* 리뷰 목록을 가져온 후 */
        /* 리뷰 작성 여부에 따라 true, false 부여 */
        for(ReviewVO reviewVO : reviewVOS) {
            if(reviewService.checkReview(reviewVO.getSeq()) > 0)
                reviewVO.setReview(true);
            else
                reviewVO.setReview(false);
        }

        model.addAttribute("reviewList", reviewVOS); /*
        String id = SecurityContextHolder.getContext().getAuthentication().getName();
        List<ReviewVO> reviewVOS = reviewService.selectReviewList(id);

        *//* 리뷰 목록을 가져온 후 *//*
        *//* 리뷰 작성 여부에 따라 true, false 부여 *//*
        for(ReviewVO reviewVO : reviewVOS) {
            if(reviewService.checkReview(reviewVO.getSeq()) > 0)
                reviewVO.setReview(true);
            else
                reviewVO.setReview(false);
        }

        model.addAttribute("reviewList", reviewVOS);*/
        return "/mypage/review/list";
    }

    /* 구매후기 작성 폼 */
    @GetMapping("/mypage/write_review")
    public String reviewWrite(@RequestParam("seq") int orderDetailsSeq
            ,Model model) {
        model.addAttribute("reviewInfo"
                , reviewService.selectReview(orderDetailsSeq));

        return "/mypage/review/form";
    }

    /* 구매후기 작성 */
    @PostMapping("/mypage/modify_review")
    public String reviewModify(ReviewDTO reviewDTO) {
       /* log.info("post review Modify");
*/
      /*  String id = SecurityContextHolder.getContext().getAuthentication().getName();
        reviewDTO.setUserId(id);

        reviewService.insertReview(reviewDTO);
*/
        int userSeq = ((UserInfo)SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal()).getSeq();
        reviewDTO.setUserSeq(userSeq);

        reviewService.insertReview2(reviewDTO);

        return "redirect:/mypage/review";
    }

    /* 후기 수정 폼*/
    @GetMapping("/mypage/modify_review")
    public String reviewModify(@RequestParam(value = "seq" , required = false) int orderDetailsSeq
            , Model model) {
        log.info("get review Modify" + orderDetailsSeq);

       int userSeq = ((UserInfo)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getSeq();
       String id = SecurityContextHolder.getContext().getAuthentication().getName();

       if(orderDetailsSeq > 0) {
           ReviewDTO reviewDTO = new ReviewDTO();
           reviewDTO.setUserId(id);
           reviewDTO.setUserSeq(userSeq);
           reviewDTO.setOrderDetailsSeq(orderDetailsSeq);

           ReviewVO reviewVO = reviewService.updateReview2(reviewDTO);
           model.addAttribute("reviewInfo", reviewVO);

           return "/mypage/review/form";
       } else {
           return "redirect:/mypage/review";
       }
    }

    /* 구매후기 삭제 */
    @GetMapping("/mypage/delete_review")
    public String reviewDelete(@RequestParam("seq") int orderDetailsSeq, Model model) {
        String id = SecurityContextHolder.getContext().getAuthentication().getName();
        int userSeq = ((UserInfo)SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal()).getSeq();

        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setUserId(id);
        reviewDTO.setUserSeq(userSeq);
        reviewDTO.setOrderDetailsSeq(orderDetailsSeq);

        reviewService.deleteReview2(reviewDTO);

        return "redirect:/mypage/review";
    }

}
