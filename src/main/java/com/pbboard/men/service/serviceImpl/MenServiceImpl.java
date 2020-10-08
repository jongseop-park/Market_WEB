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
    public List<OptionVO> option(int seq) {
        return menMapper.option(seq);
    }

    @Override
    public void registReview(ReviewVO reviewVO) {
        menMapper.registReview(reviewVO);
    }

    @Override
    public List<ReviewVO> reviewList(int seq) {
        return menMapper.reviewList(seq);
    }
}
