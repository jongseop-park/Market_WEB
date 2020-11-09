package com.pbboard.men.mapper;

import com.pbboard.men.domain.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface MenMapper {
    /* 제품 상세 조회 */
    public ProductVO selectProduct(int seq);

    /* 제품 목록 조회 */
    public List<ProductVO> selectProductList(SearchCriteria searchCriteria);

    /* 제품 개수 */
    public int countProduct(SearchCriteria searchCriteria);

    /* 옵션 조회 */
    public List<OptionVO> selectOption(int seq);

    /* 장바구니 담기 */
    public void insertCart(CartDTO cartDTO);

    /* 리뷰 목록 조회 */
    public List<ReviewVO> selectReviewList(int productSeq);

    /* 재고 확인 */
    public Integer checkStockQuantity(OptionVO optionVO);
}
