package com.ljx.hfgsjt.entity.zhcx;

import java.util.Date;

/**
 * Created by Ant on 2019/5/8.
 */
public class YjqklbModel {
    private String id;
    private String xmbh;
    private String xmmc;
    private String xmfzr;
    private String cjr;
    private String yjlx;
    private Date Yjkssj;
    private Date Yjwcsj;
    private String jsr;
    private int page=1;
    private int size=10;
    private int startRow;
    private int endRow;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getXmbh() {
        return xmbh;
    }

    public void setXmbh(String xmbh) {
        this.xmbh = xmbh;
    }

    public String getXmmc() {
        return xmmc;
    }

    public void setXmmc(String xmmc) {
        this.xmmc = xmmc;
    }

    public String getXmfzr() {
        return xmfzr;
    }

    public void setXmfzr(String xmfzr) {
        this.xmfzr = xmfzr;
    }

    public String getCjr() {
        return cjr;
    }

    public void setCjr(String cjr) {
        this.cjr = cjr;
    }

    public String getYjlx() {
        return yjlx;
    }

    public void setYjlx(String yjlx) {
        this.yjlx = yjlx;
    }

    public Date getYjkssj() {
        return Yjkssj;
    }

    public void setYjkssj(Date yjkssj) {
        Yjkssj = yjkssj;
    }

    public Date getYjwcsj() {
        return Yjwcsj;
    }

    public void setYjwcsj(Date yjwcsj) {
        Yjwcsj = yjwcsj;
    }

    public String getJsr() {
        return jsr;
    }

    public void setJsr(String jsr) {
        this.jsr = jsr;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public int getEndRow() {
        return endRow;
    }

    public void setEndRow(int endRow) {
        this.endRow = endRow;
    }
}
