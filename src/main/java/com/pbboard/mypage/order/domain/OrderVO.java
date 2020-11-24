package com.pbboard.mypage.order.domain;

public class OrderVO {

    /** 주문 번호 */
    private long orderSeq;

    /** 상품명 */
    private String productName;

    /** 상품 이미지 */
    private String productImage;

    /** 상품 브랜드 */
    private String productBrand;

    /** 옵션명 */
    private String optionName;

    /** 주문일 */
    private String regDt;

    /** 수량 */
    private int quantity;

    /** 주문 금액 */
    private String orderPrice;

    /** 주문 상태 */
    private String orderStatus;

    /** 결제 시간 */
    private Long payAt;

    /** 결제 수단 */
    private String payMethod;

    public long getOrderSeq() {
        return orderSeq;
    }

    public void setOrderSeq(long orderSeq) {
        this.orderSeq = orderSeq;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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

    public String getOptionName() {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }

    public String getRegDt() {
        return regDt;
    }

    public void setRegDt(String regDt) {
        this.regDt = regDt;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(String orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }


    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public Long getPayAt() {
        return payAt;
    }

    public void setPayAt(Long payAt) {
        this.payAt = payAt;
    }
}
