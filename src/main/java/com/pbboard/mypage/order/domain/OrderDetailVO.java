package com.pbboard.mypage.order.domain;

public class OrderDetailVO {
    /** 제품 시퀀스 */
    private int productSeq;

    /** 옵션 명 */
    private String optionName;

    /** 수량 */
    private int quantity;

    public int getProductSeq() {
        return productSeq;
    }

    public void setProductSeq(int productSeq) {
        this.productSeq = productSeq;
    }

    public String getOptionName() {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
