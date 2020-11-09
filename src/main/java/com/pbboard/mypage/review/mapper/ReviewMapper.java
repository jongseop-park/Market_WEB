package com.pbboard.mypage.review.mapper;

import com.pbboard.mypage.review.domain.ReviewDTO;
import com.pbboard.mypage.review.domain.ReviewVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ReviewMapper {
     /* 리뷰 목록 조회 */
     List<ReviewVO> selectReviewList(int userSeq);

     /* 리뷰 작성 */
     ReviewVO selectReview(int seq);

     /* 리뷰 삭제 */
     void deleteReview(ReviewDTO reviewDTO);

     /* 리뷰 등록 */
     void insertReview(ReviewDTO reviewDTO);

     /* 리뷰 수정 */
     ReviewVO updateReview(ReviewDTO reviewDTO);

     /* 리뷰 작성 여부 확인 */
     int checkReview(int seq);
}
