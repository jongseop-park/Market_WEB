package com.pbboard.mypage.review.service.serviceImpl;

import com.pbboard.mypage.review.domain.ReviewDTO;
import com.pbboard.mypage.review.domain.ReviewVO;
import com.pbboard.mypage.review.mapper.ReviewMapper;
import com.pbboard.mypage.review.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    ReviewMapper reviewMapper;

    @Autowired
    public ReviewServiceImpl(ReviewMapper reviewMapper) {
        this.reviewMapper = reviewMapper;
    }

    @Override
    public List<ReviewVO> selectReviewList(String id) {
        return reviewMapper.selectReviewList(id);
    }

    @Override
    public int checkReview(int seq) {
        return reviewMapper.checkReview(seq);
    }

    @Override
    public ReviewVO selectReview(int seq) {
        return reviewMapper.selectReview(seq);
    }

    @Override
    public void insertReview(ReviewDTO reviewDTO) {
        reviewMapper.insertReview(reviewDTO);
    }

    @Override
    public ReviewVO updateReview(ReviewDTO reviewDTO) {
        return reviewMapper.updateReview(reviewDTO);
    }

    @Override
    public void deleteReview(ReviewDTO reviewDTO) {
        reviewMapper.deleteReview(reviewDTO);
    }
}
