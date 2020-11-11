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
    public ProductVO selectProduct(int seq) throws Exception {
        List<ReviewVO> reviewVOS = menMapper.selectReviewList(seq);

        int sum = 0;
        int averageScore = 0;

        for(ReviewVO reviewVO : reviewVOS) {
            sum += reviewVO.getScore();
        }

        if(reviewVOS.size() > 0)
            averageScore = sum / reviewVOS.size();

        ProductVO productVO = menMapper.selectProduct(seq);
        productVO.setScore(averageScore);

        if(productVO == null)
            throw new Exception();

        return productVO;
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

        // out of stock check
        return productVOList;
    }

    @Override
    public int countProduct(SearchCriteria searchCriteria) {
        return menMapper.countProduct(searchCriteria);
    }

    @Override
    public List<OptionVO> selectOption(int seq) {
        List<OptionVO> optionVOSList = menMapper.selectOption(seq);
        List<String> options = new ArrayList<>();

        // 옵션 없을 경우
        if(optionVOSList.isEmpty()) {
            return null;
        }

        // 옵션 배열
        String[][] option = new String[optionVOSList.size()][];

        // 옵션값 저장
        // 1 사이즈 {S,M,L}
        // 2 색상 {'화이트', '블랙'}
        int num = 0; // 옵션 개수 확인 값
        for(OptionVO optionVO : optionVOSList) {
             option[num] = optionVO.getOptionValues();
            num++;
        }
        /* logger.info("num : " + num);*/

        // 옵션 1개 이상
        if(num > 1) {
            // 옵션 값 조합 (블랙,화이트 / S,M,L --> 블랙/S, 블랙M, 블랙L ...)
            for (int i = 0; i < option[0].length; i++) {
                for (int j = 0; j < option[1].length; j++) {
                    String value = option[0][i];
                    value += "/";
                    value += option[1][j];
                    /* logger.info("productSeq : " + seq  + "\n option : " +  value); */
                    options.add(value);
                    value = "";
                }
            }
        } else {
            for(int i=0; i< option[0].length; i++) {
                String value = option[0][i];/*
                logger.info(value);*/
                options.add(value);
            }
        }

         List<OptionVO> stockCheckOptions = new ArrayList<>();

        for(String s : options) {
            OptionVO optionVO = new OptionVO();
            optionVO.setOptionName(s);
            optionVO.setProductSeq(seq);

            Integer quantity = menMapper.checkStockQuantity(optionVO);

            OptionVO optionVO1 = new OptionVO();
            optionVO1.setQuantity(quantity == null ? 0 : quantity);

            // 체크 후 품절표시 or 그대로 저장
            if(quantity == null) {
                optionVO1.setOptionValue(s + "(품절)");
            } else if(quantity == 0) {
                optionVO1.setOptionValue(s + "(품절)");
            } else if(quantity > 0) {
                optionVO1.setOptionValue(s);
            }

            stockCheckOptions.add(optionVO1);
        }

        return stockCheckOptions;
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
    public List<QnaVO> selectQnaList(int seq) {
        SearchCriteria searchCriteria= new SearchCriteria();
        searchCriteria.setProductSeq(seq);

        List<QnaVO> qnaVOS = menMapper.selectQnaList(searchCriteria);

        return qnaVOS;
    }

    @Override
    public List<QnaVO> selectQnaList2(SearchCriteria searchCriteria) {
        return menMapper.selectQnaList2(searchCriteria);
    }

    @Override
    public int countQna(int seq) {
        return menMapper.countQna(seq);
    }
}
