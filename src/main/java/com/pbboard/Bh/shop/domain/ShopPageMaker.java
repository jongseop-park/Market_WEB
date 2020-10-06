package com.pbboard.Bh.shop.domain;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class ShopPageMaker {
    private int page;       //현재 페이지
    private int perPageNum; //페이지 당 표시할 품목 개수
    private int rowStart;   //시작 페이지
    private int rowEnd;     //끝 페이지
    private int totalCount;
    private int startPage;
    private int endPage;
    private boolean prev;
    private boolean next;
    private int displayPageNum = 5;

    public ShopPageMaker(){
        this.page = 1;
        this.perPageNum = 9;
    }

    public void setPage(int page){
        if(page <= 0){
            this.page = 1;
            return;
        }
        this.page = page;
    }

    public void setPerPageNum(int perPageNum){
        if(perPageNum <= 0 || perPageNum > 100){
            this.perPageNum = 9;
            return;
        }
    }

    public int getPageStart() {
        return (this.page - 1) * perPageNum;
    }

    public int getPerPageNum() {
        return this.perPageNum;
    }

    public int getRowStart() {
        rowStart = ((page - 1) * perPageNum) + 1;
        return rowStart;
    }

    public int getRowEnd() {
        rowEnd = rowStart + perPageNum - 1;
        return rowEnd;
    }

    @Override
    public String toString() {
        return "ShopPageMaker [page=" + page + ", perPageNum=" + perPageNum + ", rowStart=" + rowStart + ", rowEnd="
                + rowEnd + "]";
    }

    public void setTotalCount(int totalCount){
        this.totalCount = totalCount;
        calcData();
    }


    public int getTotalCount() {
        return totalCount;
    }

    public int getStartPage() {
        return startPage;
    }

    public int getEndPage() {
        return endPage;
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

    private void calcData() {
        endPage = (int) (Math.ceil(page / (double)displayPageNum) * displayPageNum);
        startPage = (endPage - displayPageNum) + 1;

        int tempEndPage = (int) (Math.ceil(totalCount / (double)perPageNum));
        if (endPage > tempEndPage) {
            endPage = tempEndPage;
        }
        prev = startPage == 1 ? false : true;
        next = endPage * perPageNum >= totalCount ? false : true;
    }

    public String makeQuery(int page) {
        UriComponents uriComponents =
                UriComponentsBuilder.newInstance()
                        .queryParam("page", page)
                        /*.queryParam("perPageNum", perPageNum)*/
                        .build();

        return uriComponents.toUriString();
    }
}
