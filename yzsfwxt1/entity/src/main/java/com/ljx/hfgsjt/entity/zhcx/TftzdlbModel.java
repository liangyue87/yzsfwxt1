package com.ljx.hfgsjt.entity.zhcx;

import java.util.Date;

/**
 * Created by Ant on 2019/5/7.
 */
public class TftzdlbModel {
    private String id;
    private String xmbh;
    private String xmmc;
    private String xmfzr;
    private String tzdbh;
    private double ytje;
    private double stje;
    private Date qrsj;
    private String sjh;
    private int  startRow;
    private int endRow;
    private int page=1;
    private int size=10;
    private Date startDate;
    private Date endDate;
    private String zt;

    public String getZt() {
        return zt;
    }

    public void setZt(String zt) {
        this.zt = zt;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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

    public String getTzdbh() {
        return tzdbh;
    }

    public void setTzdbh(String tzdbh) {
        this.tzdbh = tzdbh;
    }

    public double getYtje() {
        return ytje;
    }

    public void setYtje(double ytje) {
        this.ytje = ytje;
    }

    public double getStje() {
        return stje;
    }

    public void setStje(double stje) {
        this.stje = stje;
    }

    public Date getQrsj() {
        return qrsj;
    }

    public void setQrsj(Date qrsj) {
        this.qrsj = qrsj;
    }

    public String getSjh() {
        return sjh;
    }

    public void setSjh(String sjh) {
        this.sjh = sjh;
    }
}
