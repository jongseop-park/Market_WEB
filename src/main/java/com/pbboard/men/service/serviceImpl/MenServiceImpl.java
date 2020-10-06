package com.pbboard.men.service.serviceImpl;

import com.pbboard.men.domain.Criteria;
import com.pbboard.men.domain.GoodsVO;
import com.pbboard.men.domain.ProductVO;
import com.pbboard.men.domain.SearchCriteria;
import com.pbboard.men.mapper.MenMapper;
import com.pbboard.men.service.MenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenServiceImpl implements MenService {
    MenMapper menMapper;

    @Autowired
    public MenServiceImpl(MenMapper menMapper) {
        this.menMapper = menMapper;
    }

/*    @Override
    public List<ProductVO> list() {
        return menMapper.list();
    }

    @Override
    public List<ProductVO> listPage(Criteria criteria) {
        return menMapper.listPage(criteria);
    }

    @Override
    public int listCount() {
        return menMapper.listCount();
    }*/

    @Override
    public ProductVO detail(int seq) {
        return menMapper.detail(seq);
    }

    @Override
    public List<ProductVO> menListSearch(SearchCriteria searchCriteria) {
        return menMapper.menListSearch(searchCriteria);
    }

    @Override
    public int menSearchCount(SearchCriteria searchCriteria) {
        return menMapper.menSearchCount(searchCriteria);
    }

    @Override
    public List<GoodsVO> goods(int seq) {
        return menMapper.goods(seq);
    }
}
