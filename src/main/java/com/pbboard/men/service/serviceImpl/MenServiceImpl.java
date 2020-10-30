package com.pbboard.men.service.serviceImpl;

import com.pbboard.men.domain.*;
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

    @Override
    public ProductVO selectProduct(int seq) {
        return menMapper.selectProduct(seq);
    }

    @Override
    public List<ProductVO> selectProductList(SearchCriteria searchCriteria) {
        return menMapper.selectProductList(searchCriteria);
    }

    @Override
    public int countProduct(SearchCriteria searchCriteria) {
        return menMapper.countProduct(searchCriteria);
    }

    @Override
    public List<OptionVO> selectOption(int seq) {
        return menMapper.selectOption(seq);
    }

    @Override
    public void insertCart(CartDTO cartDTO) {
        menMapper.insertCart(cartDTO);
    }

    @Override
    public List<ReviewVO> selectReviewList(int productSeq) {
        return menMapper.selectReviewList(productSeq);
    }
}
