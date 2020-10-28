package com.pbboard.mypage.review.service;

import com.pbboard.mypage.review.domain.ReviewDTO;
import com.pbboard.mypage.review.domain.ReviewVO;

import java.util.List;

public interface ReviewService {
    public List<ReviewVO> selectReviewList(String id);
    public int checkReview(int seq);
    public ReviewVO selectReview(int seq);
    public void insertReview(ReviewDTO reviewDTO);

    public ReviewVO updateReview(ReviewDTO reviewDTO);
    public void deleteReview(ReviewDTO reviewDTO);
}
