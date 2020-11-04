package com.pbboard.men.domain;

import java.util.List;

public class OptionVO {
    /** 제품시퀀스 */
    private int productSeq;

    /** 옵션명 */
    private String optionName;

    /** 옵션값 */
    private String optionValue;

    private String[] optionValues;

    public String getOptionName() {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }

    public String getOptionValue() {
        return optionValue;
    }

    public void setOptionValue(String optionValue) {
        // {S,M,L} ==> S / M / L (콤마 기준 배열로 분리)
        optionValues = optionValue.split(",");
        this.optionValue = optionValue;
    }

    public String[] getOptionValues() {
        return optionValues;
    }

    public void setOptionValues(String[] optionValues) {
        this.optionValues = optionValues;
    }

    public int getProductSeq() {
        return productSeq;
    }

    public void setProductSeq(int productSeq) {
        this.productSeq = productSeq;
    }
}
