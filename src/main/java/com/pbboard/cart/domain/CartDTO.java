package com.pbboard.cart.domain;

public class CartDTO {
    /** 시퀀스 */
    private int seq;

    /** 상품 시퀀스 */
    private int productSeq;

    /** 장바구니 수량 */
    private int cartStock;

    /** 사이즈 시퀀스 */
    private int sizeSeq;

    /** 회원아이디 */
    private String userId;

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public int getProductSeq() {
        return productSeq;
    }

    public void setProductSeq(int productSeq) {
        this.productSeq = productSeq;
    }

    public int getCartStock() {
        return cartStock;
    }

    public void setCartStock(int cartStock) {
        this.cartStock = cartStock;
    }

    public int getSizeSeq() {
        return sizeSeq;
    }

    public void setSizeSeq(int sizeSeq) {
        this.sizeSeq = sizeSeq;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
