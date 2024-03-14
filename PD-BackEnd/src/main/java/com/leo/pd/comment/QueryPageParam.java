package com.leo.pd.comment;

import java.util.HashMap;

public class QueryPageParam {
    // default
    private static int PAGE_SIZE = 100;
    private static int PAGE_NUM = 1;

    // receive form frontEnd
    private int pageSize = PAGE_SIZE;
    private int pageNum = PAGE_NUM;

    private HashMap param = new HashMap();


    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public HashMap getParam() {
        return param;
    }

    public void setParam(HashMap param) {
        this.param = param;
    }

    @Override
    public String toString() {
        return "QueryPageParam{" +
                "pageSize=" + pageSize +
                ", pageNum=" + pageNum +
                ", param=" + param +
                '}';
    }
}
