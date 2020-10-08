package com.pbboard.men.domain;

public class Criteria {
    /** 현재 페이지 */
    private int page;

    /** 페이지당 출력할 개수 */
    private int perPageNum;

    /** 시작번호 */
    private int rowStart;

    /** 끝번호 */
    private int rowEnd;

    public Criteria()
    {
        this.page = 1;
        this.perPageNum = 9;
    }

    public void setPage(int page)
    {
        if (page <= 0)
        {
            this.page = 1;
            return;
        }
        this.page = page;
    }

    public int getPage()
    {
        return page;
    }

    public int getPageStart()
    {
        return (this.page - 1) * perPageNum;
    }

    public int getPerPageNum()
    {
        return this.perPageNum;
    }

    @Override
    public String toString() {
        return "Criteria [page=" + page + ", perPageNum=" + perPageNum + ""
                + ", rowStart=" +  getRowStart() + ", rowEnd=" + getRowEnd()
                + "]";
    }

    public int getRowStart() {
        rowStart = ((page - 1) * perPageNum) + 1;
        return rowStart;
    }

    public int getRowEnd() {
        rowEnd = rowStart + perPageNum - 1;
        return rowEnd;
    }
}
