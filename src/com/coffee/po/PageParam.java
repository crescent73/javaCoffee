package com.coffee.po;

public class PageParam {
    private Integer pageNum;//当前页的页码
    private Integer pageSize; //每页的数量

    public PageParam() {}

    public PageParam(Integer pageNum, Integer pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

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

    /**
     * 是否分页
     * 只有pageNum和pageSize都不为空且大于零时才会进行分页
     * @return true 分页 / false 不分页
     */
    public boolean isPaginate(){
        return pageNum != null && pageNum > 0 && pageSize != null && pageSize > 0;
    }

    @Override
    public String toString() {
        return "pageParam{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                '}';
    }
}
