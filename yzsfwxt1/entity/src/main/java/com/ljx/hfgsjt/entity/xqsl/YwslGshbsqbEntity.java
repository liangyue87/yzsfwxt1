package com.ljx.hfgsjt.entity.xqsl;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "YWSL_GSHBSQB")
public class YwslGshbsqbEntity {
    @Transient
    SimpleDateFormat sdft = new SimpleDateFormat("yyyy-MM-dd");

    private String id;
    private String sqbh;
    private String xmmc;
    private String lxr;
    private String lxrsjh;
    private Long yxazsl;
    private String jsyt;
    private String yhxz;
    private String yhh;
    private String ybkj;
    private String yxazkj;
    private String slr;
    private Date slrq;
    private String bgr;
    private Date bgsj;
    private String bgnr;
    private String bz;
    private String fpzt;
    private String fpr;
    private Date fpsj;
    private String xmjl;
    private String xmdz_sheng;
    private String xmdz_shi;
    private String xmdz_xzqybh;
    private String xmdz;
    private String azlx;
    private Long sqly;
    private String xzqybh;
    private String dwmc;
    private String sflx;
    private String zjlxdh;

    @Id
    @Column(name = "ID")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "SQBH")
    public String getSqbh() {
        return sqbh;
    }

    public void setSqbh(String sqbh) {
        this.sqbh = sqbh;
    }

    @Basic
    @Column(name = "XMMC")
    public String getXmmc() {
        return xmmc;
    }

    public void setXmmc(String xmmc) {
        this.xmmc = xmmc;
    }

    @Basic
    @Column(name = "LXR")
    public String getLxr() {
        return lxr;
    }

    public void setLxr(String lxr) {
        this.lxr = lxr;
    }

    @Basic
    @Column(name = "LXRSJH")
    public String getLxrsjh() {
        return lxrsjh;
    }

    public void setLxrsjh(String lxrsjh) {
        this.lxrsjh = lxrsjh;
    }

    @Basic
    @Column(name = "YXAZSL")
    public Long getYxazsl() {
        return yxazsl;
    }

    public void setYxazsl(Long yxazsl) {
        this.yxazsl = yxazsl;
    }

    @Basic
    @Column(name = "JSYT")
    public String getJsyt() {
        return jsyt;
    }

    public void setJsyt(String jsyt) {
        this.jsyt = jsyt;
    }

    @Basic
    @Column(name = "YHXZ")
    public String getYhxz() {
        return yhxz;
    }

    public void setYhxz(String yhxz) {
        this.yhxz = yhxz;
    }

    @Basic
    @Column(name = "YHH")
    public String getYhh() {
        return yhh;
    }

    public void setYhh(String yhh) {
        this.yhh = yhh;
    }

    @Basic
    @Column(name = "YBKJ")
    public String getYbkj() {
        return ybkj;
    }

    public void setYbkj(String ybkj) {
        this.ybkj = ybkj;
    }

    @Basic
    @Column(name = "YXAZKJ")
    public String getYxazkj() {
        return yxazkj;
    }

    public void setYxazkj(String yxazkj) {
        this.yxazkj = yxazkj;
    }

    @Basic
    @Column(name = "SLR")
    public String getSlr() {
        return slr;
    }

    public void setSlr(String slr) {
        this.slr = slr;
    }

    @Basic
    @Column(name = "SLRQ")
    public Date getSlrq() {
        return slrq;
    }

    public void setSlrq(Date slrq) {
        this.slrq = slrq;
    }

    @Basic
    @Column(name = "BGR")
    public String getBgr() {
        return bgr;
    }

    public void setBgr(String bgr) {
        this.bgr = bgr;
    }

    @Basic
    @Column(name = "BGSJ")
    public Date getBgsj() {
        return bgsj;
    }

    public void setBgsj(Date bgsj) {
        this.bgsj = bgsj;
    }

    @Basic
    @Column(name = "BGNR")
    public String getBgnr() {
        return bgnr;
    }

    public void setBgnr(String bgnr) {
        this.bgnr = bgnr;
    }

    @Basic
    @Column(name = "BZ")
    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    @Basic
    @Column(name = "FPZT")
    public String getFpzt() {
        return fpzt;
    }

    public void setFpzt(String fpzt) {
        this.fpzt = fpzt;
    }

    @Basic
    @Column(name = "FPR")
    public String getFpr() {
        return fpr;
    }

    public void setFpr(String fpr) {
        this.fpr = fpr;
    }

    @Basic
    @Column(name = "FPSJ")
    public Date getFpsj() {
        return fpsj;
    }

    public void setFpsj(Date fpsj) {
        this.fpsj = fpsj;
    }

    @Basic
    @Column(name = "XMJL")
    public String getXmjl() {
        return xmjl;
    }

    public void setXmjl(String xmjl) {
        this.xmjl = xmjl;
    }

    public String getXmdz_sheng() {
        return xmdz_sheng;
    }

    public void setXmdz_sheng(String xmdz_sheng) {
        this.xmdz_sheng = xmdz_sheng;
    }

    public String getXmdz_shi() {
        return xmdz_shi;
    }

    public void setXmdz_shi(String xmdz_shi) {
        this.xmdz_shi = xmdz_shi;
    }

    public String getXmdz_xzqybh() {
        return xmdz_xzqybh;
    }

    public void setXmdz_xzqybh(String xmdz_xzqybh) {
        this.xmdz_xzqybh = xmdz_xzqybh;
    }

    @Basic
    @Column(name = "XMDZ")
    public String getXmdz() {
        return xmdz;
    }

    public void setXmdz(String xmdz) {
        this.xmdz = xmdz;
    }

    @Basic
    @Column(name = "AZLX")
    public String getAzlx() {
        return azlx;
    }

    public void setAzlx(String azlx) {
        this.azlx = azlx;
    }

    @Basic
    @Column(name = "SQLY")
    public Long getSqly() {
        return sqly;
    }

    public void setSqly(Long sqly) {
        this.sqly = sqly;
    }

    @Basic
    @Column(name = "XZQYBH")
    public String getXzqybh() {
        return xzqybh;
    }

    public void setXzqybh(String xzqybh) {
        this.xzqybh = xzqybh;
    }

    @Basic
    @Column(name = "DWMC")
    public String getDwmc() {
        return dwmc;
    }

    public void setDwmc(String dwmc) {
        this.dwmc = dwmc;
    }

    public String getSflx() {
        return sflx;
    }

    public void setSflx(String sflx) {
        this.sflx = sflx;
    }

    public String getZjlxdh() {
        return zjlxdh;
    }

    public void setZjlxdh(String zjlxdh) {
        this.zjlxdh = zjlxdh;
    }

    @Override
    public String toString() {
        return "{" +
                "id:'" + id + '\'' +
                ", sqbh:'" + sqbh + '\'' +
                ", xmmc:'" + xmmc + '\'' +
                ", lxr:'" + lxr + '\'' +
                ", lxrsjh:'" + lxrsjh + '\'' +
                ", yxazsl:'" + yxazsl + '\'' +
                ", jsyt:'" + jsyt + '\'' +
                ", yhxz:'" + yhxz + '\'' +
                ", yhh:'" + yhh + '\'' +
                ", ybkj:'" + ybkj + '\'' +
                ", yxazkj:'" + yxazkj + '\'' +
                ", slr:'" + slr + '\'' +
                ", slrq:'" + sdft.format(slrq) + '\'' +
                ", bgr:'" + bgr + '\'' +
                ", bgsj:'" + bgsj + '\'' +
                ", bgnr:'" + bgnr + '\'' +
                ", bz:'" + bz + '\'' +
                ", fpzt:'" + fpzt + '\'' +
                ", fpr:'" + fpr + '\'' +
                ", fpsj:'" + fpsj + '\'' +
                ", xmjl:'" + xmjl + '\'' +
                ", xmdz_sheng:'" + xmdz_sheng + '\'' +
                ", xmdz_shi:'" + xmdz_shi + '\'' +
                ", xmdz_xzqybh:'" + xmdz_xzqybh + '\'' +
                ", xmdz:'" + xmdz + '\'' +
                ", azlx:'" + azlx + '\'' +
                ", sqly:'" + sqly + '\'' +
                ", xzqybh:'" + xzqybh + '\'' +
                ", dwmc:'" + dwmc + '\'' +
                ", sflx:'" + sflx + '\'' +
                ", zjlxdh:'" + zjlxdh + '\'' +
                '}';
    }
}
