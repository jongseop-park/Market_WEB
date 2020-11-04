package com.pbboard.men.service.serviceImpl;

import com.pbboard.men.domain.*;
import com.pbboard.men.mapper.MenMapper;
import com.pbboard.men.service.MenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class MenServiceImpl implements MenService {
    MenMapper menMapper;
    Logger logger = LoggerFactory.getLogger(getClass());

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
        List<ProductVO> productVOList = menMapper.selectProductList(searchCriteria);

        Calendar cal = Calendar.getInstance();
        DecimalFormat df = new DecimalFormat("00");

        // 2rd 파라미터, new 표시 일수
        cal.add(cal.DATE, -7);
        String strYear = Integer.toString(cal.get(Calendar.YEAR));
        String strMonth = df.format(cal.get(Calendar.MONTH) + 1);
        String strDay = df.format(cal.get(Calendar.DATE));
        String date= strYear + "-" +  strMonth  + "-" + strDay;

        // new Product check
        for(ProductVO productVO : productVOList) {
            if(date.compareTo(productVO.getRegDt()) > 0) {
                productVO.setNewProduct(false);
            } else {
                productVO.setNewProduct(true);
            }
        }

        return productVOList;
    }

    @Override
    public int countProduct(SearchCriteria searchCriteria) {
        return menMapper.countProduct(searchCriteria);
    }

    @Override
    public List<OptionVO> selectOption(int seq) {
        List<OptionVO> optionVOSList = menMapper.selectOption(seq);

        String[][] option = new String[2][];
        List<String> options = new ArrayList<>();

        int num = 0;
        for(OptionVO optionVO : optionVOSList) {
             option[num] = optionVO.getOptionValues();
            num++;
        }

        for(int i=0; i< option[0].length; i++) {
            for(int j=0; j<option[1].length; j++) {
                String value = option[0][i];
                value += "/";
                value += option[1][j];
                /* logger.info("productSeq : " + seq  + "\n option : " +  value); */
                options.add(value);
                value = "";
            }
        }

        for(String s : options) {
            logger.info(s);
        }

        return optionVOSList;
    }

    @Override
    public void insertCart(CartDTO cartDTO) {
        menMapper.insertCart(cartDTO);
    }

    @Override
    public List<ReviewVO> selectReviewList(int productSeq) {
        return menMapper.selectReviewList(productSeq);
    }

    @Override
    public void insertCart2(CartDTO cartDTO) {
        menMapper.insertCart2(cartDTO);
    }

    @Override
    public List<ReviewVO> selectReviewList2(int productSeq) {
        return menMapper.selectReviewList2(productSeq);
    }
}
