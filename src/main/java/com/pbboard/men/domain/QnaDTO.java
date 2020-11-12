package com.pbboard.men.domain;

public class QnaDTO {
    /** 문의 종류 */
    private String qnaType;

    /** 문의 제목 */
    private String qnaTitle;

    /** 문의 내용 */
    private String qnaContent;

    /** 회원 시퀀스 */
    private int userSeq;

    /** 제품 시퀀스 */
    private int productSeq;

    public String getQnaType() {
        return qnaType;
    }

    public void setQnaType(String qnaType) {
        this.qnaType = qnaType;
    }

    public String getQnaTitle() {
        return qnaTitle;
    }

    public void setQnaTitle(String qnaTitle) {
        this.qnaTitle = qnaTitle;
    }

    public String getQnaContent() {
        return qnaContent;
    }

    public void setQnaContent(String qnaContent) {
        this.qnaContent = qnaContent;
    }

    public int getProductSeq() {
        return productSeq;
    }

    public void setProductSeq(int productSeq) {
        this.productSeq = productSeq;
    }

    public int getUserSeq() {
        return userSeq;
    }

    public void setUserSeq(int userSeq) {
        this.userSeq = userSeq;
    }
}
