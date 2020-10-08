package com.pbboard.men.domain;

public class ReviewVO {
    /** 시퀀스 */
    private int seq;

    /** 멤버아이디 */
    private String memberId;

    /** 닉네임 */
    private String nickName;

    /** 주문번호 */
    private int orderSeq;

    /** 내용 */
    private String content;

    /** 점수 */
    private int score;

    /** 등록일 */
    private String regDt;

    /** 수정일 */
    private String modDt;

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
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

    public String getRegDt() {
        return regDt;
    }

    public void setRegDt(String regDt) {
        this.regDt = regDt;
    }

    public String getModDt() {
        return modDt;
    }

    public void setModDt(String modDt) {
        this.modDt = modDt;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getOrderSeq() {
        return orderSeq;
    }

    public void setOrderSeq(int orderSeq) {
        this.orderSeq = orderSeq;
    }
}
