package com.pbboard.men.domain;

import java.util.List;

public class OptionVO {
    private String optionName;
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
        // {S,M,L} ==> S / M / L
        optionValues = optionValue.split(",");
        this.optionValue = optionValue;
    }

    public String[] getOptionValues() {
        return optionValues;
    }

    public void setOptionValues(String[] optionValues) {
        this.optionValues = optionValues;
    }
}
