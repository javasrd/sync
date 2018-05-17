package com.zc.base.orm.mybatis.paging;

import java.util.List;

public class ExtStylePagingResults<T> {
    private Integer totalCount;
    private List<T> results;

    public Integer getTotalCount() {
        return this.totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getResults() {
        return this.results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }
}
