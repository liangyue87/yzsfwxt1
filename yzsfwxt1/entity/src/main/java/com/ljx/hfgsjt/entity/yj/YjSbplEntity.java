package com.ljx.hfgsjt.entity.yj;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 移交水表批量编辑公共字段实体
 */
public class YjSbplEntity {
    private String zbdz;
    private String gss;
    private String sbkj;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String azrq;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String tsrq;
    private String sbcj;
    private String sblx;
    private String sgry;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String sgrq;
    private String yhdb;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String yhqrrq;
    private String ids;

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public String getZbdz() {
        return zbdz;
    }

    public void setZbdz(String zbdz) {
        this.zbdz = zbdz;
    }

    public String getGss() {
        return gss;
    }

    public void setGss(String gss) {
        this.gss = gss;
    }

    public String getSbkj() {
        return sbkj;
    }

    public void setSbkj(String sbkj) {
        this.sbkj = sbkj;
    }

    public String getAzrq() {
        return azrq;
    }

    public void setAzrq(String azrq) {
        this.azrq = azrq;
    }

    public String getTsrq() {
        return tsrq;
    }

    public void setTsrq(String tsrq) {
        this.tsrq = tsrq;
    }

    public String getSbcj() {
        return sbcj;
    }

    public void setSbcj(String sbcj) {
        this.sbcj = sbcj;
    }

    public String getSblx() {
        return sblx;
    }

    public void setSblx(String sblx) {
        this.sblx = sblx;
    }

    public String getSgry() {
        return sgry;
    }

    public void setSgry(String sgry) {
        this.sgry = sgry;
    }

    public String getSgrq() {
        return sgrq;
    }

    public void setSgrq(String sgrq) {
        this.sgrq = sgrq;
    }

    public String getYhdb() {
        return yhdb;
    }

    public void setYhdb(String yhdb) {
        this.yhdb = yhdb;
    }

    public String getYhqrrq() {
        return yhqrrq;
    }

    public void setYhqrrq(String yhqrrq) {
        this.yhqrrq = yhqrrq;
    }
}
