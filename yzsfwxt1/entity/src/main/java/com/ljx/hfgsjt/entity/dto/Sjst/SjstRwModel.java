package com.ljx.hfgsjt.entity.dto.Sjst;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "SJST_RWB")
public class SjstRwModel {
    @Id
    private String id;
    private String fqr;
    private String bz;
    private String wtdw;
    private String fpr;
    private String fpsj;
    private String sjnr;
    private String sjkssj;
    private String sjjssj;
    private String yhlqtzsj;
    private String yhtjzlrq;
    private String sjr;
    private String sjstzt;
    private String yhsfqr;
    private String yhqrsj;
    private String ztbgrbh;
    private String ztbgsj;
    private String sjcglx;
    private String rwbh;
    private String sjrybz;
    private float sjsx;
    @Transient
    private String xmid;

    public float getSjsx() {
        return sjsx;
    }

    public void setSjsx(float sjsx) {
        this.sjsx = sjsx;
    }

    public String getRwbh() {
        return rwbh;
    }

    public void setRwbh(String rwbh) {
        this.rwbh = rwbh;
    }

    public String getSjrybz() {
        return sjrybz;
    }

    public void setSjrybz(String sjrybz) {
        this.sjrybz = sjrybz;
    }

    public String getXmid() {
        return xmid;
    }

    public void setXmid(String xmid) {
        this.xmid = xmid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFqr() {
        return fqr;
    }

    public void setFqr(String fqr) {
        this.fqr = fqr;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getWtdw() {
        return wtdw;
    }

    public void setWtdw(String wtdw) {
        this.wtdw = wtdw;
    }

    public String getFpr() {
        return fpr;
    }

    public void setFpr(String fpr) {
        this.fpr = fpr;
    }

    public String getFpsj() {
        return fpsj;
    }

    public void setFpsj(String fpsj) {
        this.fpsj = fpsj;
    }

    public String getSjnr() {
        return sjnr;
    }

    public void setSjnr(String sjnr) {
        this.sjnr = sjnr;
    }

    public String getSjkssj() {
        return sjkssj;
    }

    public void setSjkssj(String sjkssj) {
        this.sjkssj = sjkssj;
    }

    public String getSjjssj() {
        return sjjssj;
    }

    public void setSjjssj(String sjjssj) {
        this.sjjssj = sjjssj;
    }

    public String getYhlqtzsj() {
        return yhlqtzsj;
    }

    public void setYhlqtzsj(String yhlqtzsj) {
        this.yhlqtzsj = yhlqtzsj;
    }

    public String getYhtjzlrq() {
        return yhtjzlrq;
    }

    public void setYhtjzlrq(String yhtjzlrq) {
        this.yhtjzlrq = yhtjzlrq;
    }

    public String getSjr() {
        return sjr;
    }

    public void setSjr(String sjr) {
        this.sjr = sjr;
    }

    public String getSjstzt() {
        return sjstzt;
    }

    public void setSjstzt(String sjstzt) {
        this.sjstzt = sjstzt;
    }

    public String getYhsfqr() {
        return yhsfqr;
    }

    public void setYhsfqr(String yhsfqr) {
        this.yhsfqr = yhsfqr;
    }

    public String getYhqrsj() {
        return yhqrsj;
    }

    public void setYhqrsj(String yhqrsj) {
        this.yhqrsj = yhqrsj;
    }

    public String getZtbgrbh() {
        return ztbgrbh;
    }

    public void setZtbgrbh(String ztbgrbh) {
        this.ztbgrbh = ztbgrbh;
    }

    public String getZtbgsj() {
        return ztbgsj;
    }

    public void setZtbgsj(String ztbgsj) {
        this.ztbgsj = ztbgsj;
    }

    public String getSjcglx() {
        return sjcglx;
    }

    public void setSjcglx(String sjcglx) {
        this.sjcglx = sjcglx;
    }
}
