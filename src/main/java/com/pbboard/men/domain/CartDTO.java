package com.pbboard.men.domain;

public class CartDTO {
    /** 장바구니 시퀀스 */
    private int seq;

    /** 제품번호 */
    private int productSeq;

    /** 회원 아이디 */
    private String memberId;

    /** 제품 수량 */
    private int quantity;

    /** 옵션명 */
    private String optionName;

    public int getProductSeq() {
        return productSeq;
    }

    public void setProductSeq(int productSeq) {
        this.productSeq = productSeq;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getOptionName() {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }
}
