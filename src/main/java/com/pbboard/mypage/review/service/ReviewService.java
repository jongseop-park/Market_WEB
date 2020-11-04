package com.pbboard.mypage.review.service;

import com.pbboard.mypage.review.domain.ReviewDTO;
import com.pbboard.mypage.review.domain.ReviewVO;

import java.util.List;

public interface ReviewService {
    public List<ReviewVO> selectReviewList(String id);
    public List<ReviewVO> selectReviewList2(int userSeq);

    public void insertReview(ReviewDTO reviewDTO);
    public void insertReview2(ReviewDTO reviewDTO);

    public ReviewVO updateReview(ReviewDTO reviewDTO);
    public ReviewVO updateReview2(ReviewDTO reviewDTO);

    public int checkReview(int seq);

    public ReviewVO selectReview(int seq);

    public void deleteReview(ReviewDTO reviewDTO);
    public void deleteReview2(ReviewDTO reviewDTO);
}
