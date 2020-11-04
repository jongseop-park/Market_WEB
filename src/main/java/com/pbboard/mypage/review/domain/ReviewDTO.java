package com.pbboard.mypage.review.domain;

public class ReviewDTO {
    /** 회원 아이디 */
    private String userId;

    /** 내용 */
    private String content;

    /** 평점 */
    private Integer score;

    /** 주문 상세 번호 */
    private int orderDetailsSeq;

    /** 회원 시퀀스 */
    private int userSeq;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public int getOrderDetailsSeq() {
        return orderDetailsSeq;
    }

    public void setOrderDetailsSeq(int orderDetailsSeq) {
        this.orderDetailsSeq = orderDetailsSeq;
    }

    public int getUserSeq() {
        return userSeq;
    }

    public void setUserSeq(int userSeq) {
        this.userSeq = userSeq;
    }
}
