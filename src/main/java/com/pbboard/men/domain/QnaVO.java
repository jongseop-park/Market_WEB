package com.pbboard.men.domain;

public class QnaVO {
    /** 시퀀스 */
    private int seq;

    /** 답변 여부 */
    private Boolean ansYn;

    /** 문의 구분 */
    private String qnaType;

    /** 문의 제목 */
    private String qnaTitle;

    /** 문의 내용 */
    private String qnaContent;

    /** 회원 아이디 */
    private String userId;

    /** 등록일자 */
    private String regDt;

    /** 답변 내용 */
    private String replyContent;

    /** 답변 일자 */
    private String replyDt;

    /** 답변 회원 닉네임 */
    private String replyUserNickName;

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public String getAnsYn() {
        if(ansYn)
            return "답변완료";
        else
            return "답변예정";
    }

    public void setAnsYn(Boolean ansYn) {
        this.ansYn = ansYn;
    }

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRegDt() {
        return regDt;
    }

    public void setRegDt(String regDt) {
        this.regDt = regDt;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public String getReplyDt() {
        return replyDt;
    }

    public void setReplyDt(String replyDt) {
        this.replyDt = replyDt;
    }

    public String getReplyUserNickName() {
        return replyUserNickName;
    }

    public void setReplyUserNickName(String replyUserNickName) {
        this.replyUserNickName = replyUserNickName;
    }
}
