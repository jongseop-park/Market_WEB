package com.pbboard.mypage.review.service;

import com.pbboard.mypage.review.domain.ReviewDTO;
import com.pbboard.mypage.review.domain.ReviewVO;

import java.util.List;

public interface ReviewService {
    /* 리뷰 목록 조회 */
    public List<ReviewVO> selectReviewList(int userSeq);

    /* 리뷰 생성 */
    public void insertReview(ReviewDTO reviewDTO);

    /* 리뷰 수정 */
    public ReviewVO updateReview(ReviewDTO reviewDTO);

    /* 리뷰 작성 여부 확인 */
    public int checkReview(int seq);

    /* 리뷰 조회 */
    public ReviewVO selectReview(int seq);

    /* 리뷰 삭제 */
    public void deleteReview(ReviewDTO reviewDTO);
}
