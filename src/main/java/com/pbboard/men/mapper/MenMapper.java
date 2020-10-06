package com.pbboard.men.mapper;

import com.pbboard.men.domain.Criteria;
import com.pbboard.men.domain.GoodsVO;
import com.pbboard.men.domain.ProductVO;
import com.pbboard.men.domain.SearchCriteria;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface MenMapper {
/*    public List<ProductVO> list();

    public List<ProductVO> listPage(Criteria criteria);

    public int listCount();
    */

    /* 상세 조회 */
    public ProductVO detail(int seq);

    /* 목록 조회 */
    public List<ProductVO> menListSearch(SearchCriteria searchCriteria);

    /* 목록 개수 */
    public int menSearchCount(SearchCriteria searchCriteria);

    public List<GoodsVO> goods(int seq);
}
