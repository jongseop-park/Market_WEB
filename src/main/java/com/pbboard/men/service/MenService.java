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

    public List<OptionVO> option(int seq);

    public void registReview(ReviewVO reviewVO);

    public List<ReviewVO> reviewList(int seq);

    public void addCart(CartDTO cartDTO);
}
