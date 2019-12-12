package com.coffee.kit;

import com.github.pagehelper.PageInfo;

public class Data<T> {

    private T data;
    private Integer pageNum;//当前页的页码
    private Integer pageSize; //每页的数量
    private Integer size; //当前页的数量
    private Long total; //总记录数
    private Integer pages; //总页数

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setData(PageInfo pageInfo){
        this.pageNum = pageInfo.getPageNum();
        this.pageSize = pageInfo.getPageSize();
        this.size = pageInfo.getSize();
        this.total = pageInfo.getTotal();
        this.pages = pageInfo.getPages();
        System.out.println("setData"+toString());
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    @Override
    public String toString() {
        return "Data{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", size=" + size +
                ", total=" + total +
                ", pages=" + pages +
                ", data=" + data +
                '}';
    }
}
