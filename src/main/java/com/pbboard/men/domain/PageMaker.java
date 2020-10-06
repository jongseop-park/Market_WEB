package com.pbboard.men.domain;

import com.pbboard.men.domain.Criteria;

public class PageMaker {

    /** 전체 게시물 수  */
    private int totalCount;

    /** 시작 페이지 */
    private int startPage;

    /** 끝 페이지 */
    private int endPage;

    /** 다음 버튼 여부 */
    private boolean prev;

    /** 이전 버튼 여부 */
    private boolean next;

    /** 한 페이지에 표시될 페이지 개수 */
    private int displayPageNum = 3;

    private com.pbboard.men.domain.SearchCriteria cri;

    public void setCri(com.pbboard.men.domain.SearchCriteria cri) {
        this.cri = cri;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        calcData();
    }

    public int getTotalCount() {
        return totalCount;
    }

    public int getStartPage() {
        return startPage;
    }

    public int getEndPage() { return endPage;
    }

    public boolean isPrev() {
        return prev;
    }

    public boolean isNext() {
        return next;
    }

    public int getDisplayPageNum() {
        return displayPageNum;
    }

    public Criteria getCri() {
        return cri;
    }


    private void calcData() {
        endPage = (int) (Math.ceil(cri.getPage() / (double)displayPageNum) * displayPageNum);
        startPage = (endPage - displayPageNum) + 1;
        //startPage  = ((cri.getPage()  -1) / displayPageNum)  * displayPageNum + 1;

        // 총 페이지
        int tempEndPage = (int) (Math.ceil(totalCount / (double)cri.getPerPageNum()));

        if (endPage > tempEndPage)
            endPage = tempEndPage;

        prev = startPage == 1 ? false : true;
        next = endPage * cri.getPerPageNum() >= totalCount ? false : true;

        // 만약 전체페이지수보다 현재페이지가 클 경우
        if(tempEndPage < cri.getPage()) {
            totalCount= 0;
            prev = false;
            next = false;
        }
    }
}
