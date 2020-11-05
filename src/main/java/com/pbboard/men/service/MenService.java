package com.pbboard.men.service;

import com.pbboard.men.domain.*;

import java.util.List;

public interface MenService {
    /* 제품 상세 조회 */
    public ProductVO selectProduct(int seq) throws Exception;

    /* 제품 목록 조회 */
    public List<ProductVO> selectProductList(SearchCriteria searchCriteria);

    /* 제품 목록 개수 */
    public int countProduct(SearchCriteria searchCriteria);

    /* 옵션 조회 */
    public List<OptionVO> selectOption(int seq);

    /* 장바구니 담기 */
    public void insertCart(CartDTO cartDTO);
   public void insertCart2(CartDTO cartDTO);

    /* 리뷰 목록 조회 */
    public List<ReviewVO> selectReviewList(int productSeq);
    public List<ReviewVO> selectReviewList2(int productSeq);

}
