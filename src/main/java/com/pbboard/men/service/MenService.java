package com.pbboard.men.service;

import com.pbboard.men.domain.*;

import java.util.List;

public interface MenService {
    /* 상세 조회 */
    public ProductVO detail(int seq);

    /* 목록 조회 */
    public List<ProductVO> menListSearch(SearchCriteria searchCriteria);

    /* 목록 개수 */
    public int menSearchCount(SearchCriteria searchCriteria);

    /* 옵션 조회 */
    public List<OptionVO> option(int seq);

    /* 리뷰 등록 */
    public void registReview(ReviewVO reviewVO);

    /* 리뷰 목록 */
    public List<ReviewVO> reviewList(int seq);

    /* 장바구니 담기 */
    public void addCart(CartDTO cartDTO);
}
