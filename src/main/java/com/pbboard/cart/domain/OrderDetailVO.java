package com.pbboard.cart.domain;

public class OrderDetailVO {
    /** 시퀀스 */
    private int seq;

    /** 주문 시퀀스 */
    private Long orderSeq;

    /** 제품 시퀀스 */
    private int productSeq;

    /** 옵션 */
    private String optionName;

    /** 수량 */
    private int quantity;

    /** 회원아이디 */
    private String userId;

    /** 주문 상태 */
    private String orderStatus;

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public Long getOrderSeq() {
        return orderSeq;
    }

    public void setOrderSeq(Long orderSeq) {
        this.orderSeq = orderSeq;
    }

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
}
