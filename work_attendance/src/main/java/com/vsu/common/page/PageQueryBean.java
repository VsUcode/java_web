package com.vsu.common.page;

/**
 * Created by vsu on 2018/03/17.
 */


import java.util.Collections;
import java.util.List;

/**
 * @Author: vsu
 * @Descriptioon: 分页查询 bean
 * @Date: 2018/03/17
 */
public class PageQueryBean {

    //默认每页多少条数据
    private static final int DEFAULT_PAGE_SIZE = 10;

    /** 当前页 */
    private Integer currentPage;

    /** 每页显示数据条数 */
    private Integer pageSize;

    /** 所有记录数 */
    private int totalRows;

    /** sql查询起始行 */
    private Integer startRow;

    /** 总页数 */
    private Integer totalPage;

    /** 查询所得数据集 */
    private List<?> items;

    public final int getPageSize() {
        return pageSize == null ? DEFAULT_PAGE_SIZE : pageSize;
    }

    public final Integer getCurrentPage() {
        return currentPage;
    }

    public final void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public final void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public final int getTotalRows() {
        return totalRows;
    }

    public final void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
        int totalPage = totalRows % getPageSize() == 0 ? totalRows / getPageSize() : totalRows / getPageSize() + 1;
        setTotalPage(totalPage);
    }

    public final Integer getStartRow() {
        if (startRow == null)
            startRow = (currentPage == null ? 0 : (currentPage - 1) * getPageSize()) ;
        return startRow;
    }

    public void setStartRow(Integer startRow) {
        this.startRow = startRow;
    }

    public final Integer getTotalPage() {
        return totalPage == null || totalPage == 0 ? 1 : totalPage;
    }

    public final void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public final List<?> getItems() {
        return items == null ? Collections.EMPTY_LIST : items;

    }

    public final void setItems(List<?> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        String str = "PageQueryBean [currentPage=" + currentPage + ", pageSize="
                + pageSize + ", totalRows=" + totalRows + ", startRow="
                + startRow + ", totalPage=" + totalPage + ", items=" + items
                + "]";
//        return "PageQueryBean [currentPage=" + currentPage + ", pageSize="
//                + pageSize + ", totalRows=" + totalRows + ", startRow="
//                + startRow + ", totalPage=" + totalPage + ", items=" + items
//                + "]";
        return str;
    }
}
