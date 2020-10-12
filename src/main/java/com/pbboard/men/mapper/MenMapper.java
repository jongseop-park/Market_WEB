package com.pbboard.men.mapper;

import com.pbboard.men.domain.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface MenMapper {
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
