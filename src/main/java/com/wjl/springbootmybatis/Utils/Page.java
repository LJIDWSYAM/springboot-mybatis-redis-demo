package com.wjl.springbootmybatis.Utils;

public class Page {

    private int pageNum; //页码

    private int pageSize; //每页记录数

    private int pageIndex;//起始索引

    private int totalCount; //总记录数

    private int totalPage;//总页数

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageIndex() {

        pageIndex = (pageNum-1) * pageSize;

        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {

        if(totalCount % pageSize == 0){
            totalPage = totalCount / pageSize;
        }else{
            totalPage = totalCount / pageSize + 1;
        }

        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
}
