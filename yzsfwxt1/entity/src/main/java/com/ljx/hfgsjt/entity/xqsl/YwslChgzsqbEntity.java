package com.ljx.hfgsjt.entity.xqsl;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "YWSL_CHGZSQB")
public class YwslChgzsqbEntity {
    @Transient
    SimpleDateFormat sdft = new SimpleDateFormat("yyyy-MM-dd");

    private String id;
    private String sqbh;
    private String dyzbhh;
    private String ldzbhh;
    private String xmmc;
    private String dwmc;
    private String xysfs;
    private String xyszk;
    private String xhxzk;
    private String lxr;
    private String lxrsjh;
    private String xmdz_sheng;
    private String xmdz_shi;
    private String xmdz_xzqybh;
    private String xmdz;
    private long lfhs;
    private long pfhs;
    private long pfds;
    private long lfds;
    private long zgcs;
    private long xfyss;
    private long ckyss;
    private long cpyss;
    private long mmyss;
    private long gcyss;
    private long wyyss;
    private long qtyss;
    private String slr;
    private Date slrq;
    private String bgr;
    private Date bgsj;
    private String bgnr;
    private long bgzt;
    private String bz;
    private long dyzbxh;
    private long ldzbxh;
    private String lxdh;
    private String xzqybh;
    private String fpzt;
    private String fpr;
    private Date fpsj;
    private String xmjl;
    private String azlx;
    private Long sqly;
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
    @Column(name = "DYZBHH")
    public String getDyzbhh() {
        return dyzbhh;
    }

    public void setDyzbhh(String dyzbhh) {
        this.dyzbhh = dyzbhh;
    }

    @Basic
    @Column(name = "LDZBHH")
    public String getLdzbhh() {
        return ldzbhh;
    }

    public void setLdzbhh(String ldzbhh) {
        this.ldzbhh = ldzbhh;
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
    @Column(name = "DWMC")
    public String getDwmc() {
        return dwmc;
    }

    public void setDwmc(String dwmc) {
        this.dwmc = dwmc;
    }

    @Basic
    @Column(name = "XYSFS")
    public String getXysfs() {
        return xysfs;
    }

    public void setXysfs(String xysfs) {
        this.xysfs = xysfs;
    }

    @Basic
    @Column(name = "XYSZK")
    public String getXyszk() {
        return xyszk;
    }

    public void setXyszk(String xyszk) {
        this.xyszk = xyszk;
    }

    @Basic
    @Column(name = "XHXZK")
    public String getXhxzk() {
        return xhxzk;
    }

    public void setXhxzk(String xhxzk) {
        this.xhxzk = xhxzk;
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
    @Column(name = "LFHS")
    public long getLfhs() {
        return lfhs;
    }

    public void setLfhs(long lfhs) {
        this.lfhs = lfhs;
    }

    @Basic
    @Column(name = "PFHS")
    public long getPfhs() {
        return pfhs;
    }

    public void setPfhs(long pfhs) {
        this.pfhs = pfhs;
    }

    @Basic
    @Column(name = "PFDS")
    public long getPfds() {
        return pfds;
    }

    public void setPfds(long pfds) {
        this.pfds = pfds;
    }

    @Basic
    @Column(name = "LFDS")
    public long getLfds() {
        return lfds;
    }

    public void setLfds(long lfds) {
        this.lfds = lfds;
    }

    @Basic
    @Column(name = "ZGCS")
    public long getZgcs() {
        return zgcs;
    }

    public void setZgcs(long zgcs) {
        this.zgcs = zgcs;
    }

    @Basic
    @Column(name = "XFYSS")
    public long getXfyss() {
        return xfyss;
    }

    public void setXfyss(long xfyss) {
        this.xfyss = xfyss;
    }

    @Basic
    @Column(name = "CKYSS")
    public long getCkyss() {
        return ckyss;
    }

    public void setCkyss(long ckyss) {
        this.ckyss = ckyss;
    }

    @Basic
    @Column(name = "CPYSS")
    public long getCpyss() {
        return cpyss;
    }

    public void setCpyss(long cpyss) {
        this.cpyss = cpyss;
    }

    @Basic
    @Column(name = "MMYSS")
    public long getMmyss() {
        return mmyss;
    }

    public void setMmyss(long mmyss) {
        this.mmyss = mmyss;
    }

    @Basic
    @Column(name = "GCYSS")
    public long getGcyss() {
        return gcyss;
    }

    public void setGcyss(long gcyss) {
        this.gcyss = gcyss;
    }

    @Basic
    @Column(name = "WYYSS")
    public long getWyyss() {
        return wyyss;
    }

    public void setWyyss(long wyyss) {
        this.wyyss = wyyss;
    }

    @Basic
    @Column(name = "QTYSS")
    public long getQtyss() {
        return qtyss;
    }

    public void setQtyss(long qtyss) {
        this.qtyss = qtyss;
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
    @Column(name = "BGZT")
    public long getBgzt() {
        return bgzt;
    }

    public void setBgzt(long bgzt) {
        this.bgzt = bgzt;
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
    @Column(name = "DYZBXH")
    public long getDyzbxh() {
        return dyzbxh;
    }

    public void setDyzbxh(long dyzbxh) {
        this.dyzbxh = dyzbxh;
    }

    @Basic
    @Column(name = "LDZBXH")
    public long getLdzbxh() {
        return ldzbxh;
    }

    public void setLdzbxh(long ldzbxh) {
        this.ldzbxh = ldzbxh;
    }

    @Basic
    @Column(name = "LXDH")
    public String getLxdh() {
        return lxdh;
    }

    public void setLxdh(String lxdh) {
        this.lxdh = lxdh;
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
                ", dyzbhh:'" + dyzbhh + '\'' +
                ", ldzbhh:'" + ldzbhh + '\'' +
                ", xmmc:'" + xmmc + '\'' +
                ", dwmc:'" + dwmc + '\'' +
                ", xysfs:'" + xysfs + '\'' +
                ", xyszk:'" + xyszk + '\'' +
                ", xhxzk:'" + xhxzk + '\'' +
                ", lxr:'" + lxr + '\'' +
                ", lxrsjh:'" + lxrsjh + '\'' +
                ", xmdz_sheng:'" + xmdz_sheng + '\'' +
                ", xmdz_shi:'" + xmdz_shi + '\'' +
                ", xmdz_xzqybh:'" + xmdz_xzqybh + '\'' +
                ", xmdz:'" + xmdz + '\'' +
                ", lfhs:'" + lfhs + '\'' +
                ", pfhs:'" + pfhs + '\'' +
                ", pfds:'" + pfds + '\'' +
                ", lfds:'" + lfds + '\'' +
                ", zgcs:'" + zgcs + '\'' +
                ", xfyss:'" + xfyss + '\'' +
                ", ckyss:'" + ckyss + '\'' +
                ", cpyss:'" + cpyss + '\'' +
                ", mmyss:'" + mmyss + '\'' +
                ", gcyss:'" + gcyss + '\'' +
                ", wyyss:'" + wyyss + '\'' +
                ", qtyss:'" + qtyss + '\'' +
                ", slr:'" + slr + '\'' +
                ", slrq:'" + sdft.format(slrq) + '\'' +
                ", bgr:'" + bgr + '\'' +
                ", bgsj:'" + bgsj + '\'' +
                ", bgnr:'" + bgnr + '\'' +
                ", bgzt:'" + bgzt + '\'' +
                ", bz:'" + bz + '\'' +
                ", dyzbxh:'" + dyzbxh + '\'' +
                ", ldzbxh:'" + ldzbxh + '\'' +
                ", lxdh:'" + lxdh + '\'' +
                ", xzqybh:'" + xzqybh + '\'' +
                ", fpzt:'" + fpzt + '\'' +
                ", fpr:'" + fpr + '\'' +
                ", fpsj:'" + fpsj + '\'' +
                ", xmjl:'" + xmjl + '\'' +
                ", azlx:'" + azlx + '\'' +
                ", sqly:'" + sqly + '\'' +
                ", sflx:'" + sflx + '\'' +
                ", zjlxdh:'" + zjlxdh + '\'' +
                '}';
    }
}
