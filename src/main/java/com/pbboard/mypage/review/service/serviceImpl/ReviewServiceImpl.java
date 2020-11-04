package com.pbboard.mypage.review.service.serviceImpl;

import com.pbboard.mypage.review.domain.ReviewDTO;
import com.pbboard.mypage.review.domain.ReviewVO;
import com.pbboard.mypage.review.mapper.ReviewMapper;
import com.pbboard.mypage.review.service.ReviewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    ReviewMapper reviewMapper;
    Logger logger = LoggerFactory.getLogger(getClass());

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

    @Override
    public List<ReviewVO> selectReviewList2(int userSeq) {
        return reviewMapper.selectReviewList2(userSeq);
    }

    @Override
    public void insertReview2(ReviewDTO reviewDTO) {
        reviewMapper.insertReview2(reviewDTO);
    }

    @Override
    public ReviewVO updateReview2(ReviewDTO reviewDTO) {
        return reviewMapper.updateReview2(reviewDTO);
    }

    @Override
    public void deleteReview2(ReviewDTO reviewDTO) {
        reviewMapper.deleteReview2(reviewDTO);
    }
}
