package com.pbboard.mypage.review.domain;

public class ReviewVO {
    /** 주문상세 시퀀스 */
    private int seq;

    /** 이미지 경로 */
    private String productImage;

    /** 브랜드 */
    private String productBrand;

    /** 상품명 */
    private String productName;

    /** 옵션명 */
    private String optionName;

    /** 주문일자 */
    private String regDt;

    /** 리뷰 작성 여부 */
    private boolean isReview;

    /** 리뷰 내용 */
    private String content;

    /** 평점 */
    private int score;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getOptionName() {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
    }

    public String getRegDt() {
        return regDt;
    }

    public void setRegDt(String regDt) {
        this.regDt = regDt;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isReview() {
        return isReview;
    }

    public void setReview(boolean review) {
        isReview = review;
    }
}
