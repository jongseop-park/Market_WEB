package com.pbboard.cart.domain;

public class OrderVO {
    /** 시퀀스 */
    private Long seq;

    /** 유저아이디 */
    private String userId;

    /** 이름(수령인) */
    private String name;

    /** 주소 */
    private String address;

    /** 연락처 */
    private String phone;

    /** 주문 날짜 */
    private String regDt;

    /** 메모 */
    private String note;

    /** 총 가격 */
    private String totalPrice;

    public Long getSeq() {
        return seq;
    }

    public void setSeq(Long seq) {
        this.seq = seq;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRegDt() {
        return regDt;
    }

    public void setRegDt(String regDt) {
        this.regDt = regDt;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }
}