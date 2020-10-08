package com.pbboard.men.domain;

public class ProductVO {
    /** 시퀀스 */
    private int seq;

    /** 제품명 */
    private String name;

    /** 가격 */
    private String price;

    /** 이미지 */
    private String image;

    /** 카테고리 코드 */
    private String catecode;

    /** 요약설명 */
    private String summaryDesc;

    /** 상세설명 */
    private String detailDesc;

    /** 브랜드 */
    private String brand;

    /** 상세 이미지 1 */
    private String subImage1;
    /** 상세 이미지 2 */
    private String subImage2;
    /** 상세 이미지 3 */
    private String subImage3;
    /** 상세 이미지 4 */
    private String subImage4;


    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCatecode() {
        return catecode;
    }

    public void setCatecode(String catecode) {
        this.catecode = catecode;
    }

    public String getSummaryDesc() {
        return summaryDesc;
    }

    public void setSummaryDesc(String summaryDesc) {
        this.summaryDesc = summaryDesc;
    }

    public String getDetailDesc() {
        return detailDesc;
    }

    public void setDetailDesc(String detailDesc) {
        this.detailDesc = detailDesc;
    }

    public String getSubImage1() {
        return subImage1;
    }

    public void setSubImage1(String subImage1) {
        this.subImage1 = subImage1;
    }

    public String getSubImage2() {
        return subImage2;
    }

    public void setSubImage2(String subImage2) {
        this.subImage2 = subImage2;
    }

    public String getSubImage3() {
        return subImage3;
    }

    public void setSubImage3(String subImage3) {
        this.subImage3 = subImage3;
    }

    public String getSubImage4() {
        return subImage4;
    }

    public void setSubImage4(String subImage4) {
        this.subImage4 = subImage4;
    }
}
