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

    /** 설명 */
    private String description;

    /** 브랜드 */
    private String brand;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
}
