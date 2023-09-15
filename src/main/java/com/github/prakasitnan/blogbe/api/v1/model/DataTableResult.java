package com.github.prakasitnan.blogbe.api.v1.model;

import java.util.List;

public class DataTableResult {

    private Integer draw;
    private Integer pages;
    private Integer recordsTotal;
    private int currentTotal;
    private List data;

    public DataTableResult(Integer pages, Integer recordsTotal, int currentTotal, List data) {
        this.pages = pages;
        this.recordsTotal = recordsTotal;
        this.currentTotal = currentTotal;
        this.data = data;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Integer getDraw() {
        return draw;
    }

    public void setDraw(Integer draw) {
        this.draw = draw;
    }

    public Integer getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(Integer recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }

    public int getCurrentTotal() {
        return currentTotal;
    }

    public void setCurrentTotal(int currentTotal) {
        this.currentTotal = currentTotal;
    }
}
