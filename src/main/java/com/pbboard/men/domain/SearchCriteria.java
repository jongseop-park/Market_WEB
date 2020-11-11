package com.pbboard.men.domain;


public class SearchCriteria extends Criteria {
    private String searchType="";
    private String keyword="";
    private String category="";
    private int productSeq;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSearchType() {
        return searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String toString() {
        return "SearchCriteria{" +
                "searchType='" + searchType + '\'' +
                ", keyword='" + keyword + '\'' +
                ", category='" + category + '\'' +
                '}';
    }

    public int getProductSeq() {
        return productSeq;
    }

    public void setProductSeq(int productSeq) {
        this.productSeq = productSeq;
    }
}
