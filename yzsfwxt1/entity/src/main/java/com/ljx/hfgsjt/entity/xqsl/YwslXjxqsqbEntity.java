package com.ljx.hfgsjt.entity.xqsl;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "YWSL_XJXQSQB")
public class YwslXjxqsqbEntity {
    @Transient
    SimpleDateFormat sdft = new SimpleDateFormat("yyyy-MM-dd");
    @Id
    private String id;
    private String sqbh;
    private String xmmc;
    private String kfsmc;
    private String dchs;
    private String gchs;
    private String lxr;
    private String lxrsjh;
    private String slr;
    private Date slrq;
    private String bgr;
    private Date bgsj;
    private String bgnr;
    private String bz;
    private String xzqybh;
    private String fpzt;
    private String fpr;
    private Date fpsj;
    private String xmjl;
    private String xmdz_sheng;
    private String xmdz_shi;
    private String xmdz_xzqybh;
    private String xmdz;
    private String azlx;
    private String zjzmj;
    private String zzjzmj;
    private String zgcs;
    private String sqly;
    private String yhhh;
    private String yhhm;
    private String sbkj;
    private String jsyt;
    private String sblx;
    private String yhh;
    private String ybkj;
    private String dwmc;
    private String yhxz;
    private String dkjc;
    private String hs;
    private String sjfw;
    private String dylh;
    private String ghbfs;
    private Date jfsj;
    private String fqqk;
    private String sflx;
    private String zjlxdh;

    public SimpleDateFormat getSdft() {
        return sdft;
    }

    public void setSdft(SimpleDateFormat sdft) {
        this.sdft = sdft;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSqbh() {
        return sqbh;
    }

    public void setSqbh(String sqbh) {
        this.sqbh = sqbh;
    }

    public String getXmmc() {
        return xmmc;
    }

    public void setXmmc(String xmmc) {
        this.xmmc = xmmc;
    }

    public String getKfsmc() {
        return kfsmc;
    }

    public void setKfsmc(String kfsmc) {
        this.kfsmc = kfsmc;
    }

    public String getDchs() {
        return dchs;
    }

    public void setDchs(String dchs) {
        this.dchs = dchs;
    }

    public String getGchs() {
        return gchs;
    }

    public void setGchs(String gchs) {
        this.gchs = gchs;
    }

    public String getLxr() {
        return lxr;
    }

    public void setLxr(String lxr) {
        this.lxr = lxr;
    }

    public String getLxrsjh() {
        return lxrsjh;
    }

    public void setLxrsjh(String lxrsjh) {
        this.lxrsjh = lxrsjh;
    }

    public String getSlr() {
        return slr;
    }

    public void setSlr(String slr) {
        this.slr = slr;
    }

    public Date getSlrq() {
        return slrq;
    }

    public void setSlrq(Date slrq) {
        this.slrq = slrq;
    }

    public String getBgr() {
        return bgr;
    }

    public void setBgr(String bgr) {
        this.bgr = bgr;
    }

    public Date getBgsj() {
        return bgsj;
    }

    public void setBgsj(Date bgsj) {
        this.bgsj = bgsj;
    }

    public String getBgnr() {
        return bgnr;
    }

    public void setBgnr(String bgnr) {
        this.bgnr = bgnr;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getXzqybh() {
        return xzqybh;
    }

    public void setXzqybh(String xzqybh) {
        this.xzqybh = xzqybh;
    }

    public String getFpzt() {
        return fpzt;
    }

    public void setFpzt(String fpzt) {
        this.fpzt = fpzt;
    }

    public String getFpr() {
        return fpr;
    }

    public void setFpr(String fpr) {
        this.fpr = fpr;
    }

    public Date getFpsj() {
        return fpsj;
    }

    public void setFpsj(Date fpsj) {
        this.fpsj = fpsj;
    }

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

    public String getXmdz() {
        return xmdz;
    }

    public void setXmdz(String xmdz) {
        this.xmdz = xmdz;
    }

    public String getAzlx() {
        return azlx;
    }

    public void setAzlx(String azlx) {
        this.azlx = azlx;
    }

    public String getZjzmj() {
        return zjzmj;
    }

    public void setZjzmj(String zjzmj) {
        this.zjzmj = zjzmj;
    }

    public String getZzjzmj() {
        return zzjzmj;
    }

    public void setZzjzmj(String zzjzmj) {
        this.zzjzmj = zzjzmj;
    }

    public String getZgcs() {
        return zgcs;
    }

    public void setZgcs(String zgcs) {
        this.zgcs = zgcs;
    }

    public String getSqly() {
        return sqly;
    }

    public void setSqly(String sqly) {
        this.sqly = sqly;
    }

    public String getYhhh() {
        return yhhh;
    }

    public void setYhhh(String yhhh) {
        this.yhhh = yhhh;
    }

    public String getYhhm() {
        return yhhm;
    }

    public void setYhhm(String yhhm) {
        this.yhhm = yhhm;
    }

    public String getSbkj() {
        return sbkj;
    }

    public void setSbkj(String sbkj) {
        this.sbkj = sbkj;
    }

    public String getJsyt() {
        return jsyt;
    }

    public void setJsyt(String jsyt) {
        this.jsyt = jsyt;
    }

    public String getSblx() {
        return sblx;
    }

    public void setSblx(String sblx) {
        this.sblx = sblx;
    }

    public String getYhh() {
        return yhh;
    }

    public void setYhh(String yhh) {
        this.yhh = yhh;
    }

    public String getYbkj() {
        return ybkj;
    }

    public void setYbkj(String ybkj) {
        this.ybkj = ybkj;
    }

    public String getDwmc() {
        return dwmc;
    }

    public void setDwmc(String dwmc) {
        this.dwmc = dwmc;
    }

    public String getYhxz() {
        return yhxz;
    }

    public void setYhxz(String yhxz) {
        this.yhxz = yhxz;
    }

    public String getDkjc() {
        return dkjc;
    }

    public void setDkjc(String dkjc) {
        this.dkjc = dkjc;
    }

    public String getHs() {
        return hs;
    }

    public void setHs(String hs) {
        this.hs = hs;
    }

    public String getSjfw() {
        return sjfw;
    }

    public void setSjfw(String sjfw) {
        this.sjfw = sjfw;
    }

    public String getDylh() {
        return dylh;
    }

    public void setDylh(String dylh) {
        this.dylh = dylh;
    }

    public String getGhbfs() {
        return ghbfs;
    }

    public void setGhbfs(String ghbfs) {
        this.ghbfs = ghbfs;
    }

    public Date getJfsj() {
        return jfsj;
    }

    public void setJfsj(Date jfsj) {
        this.jfsj = jfsj;
    }

    public String getFqqk() {
        return fqqk;
    }

    public void setFqqk(String fqqk) {
        this.fqqk = fqqk;
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
                ", kfsmc:'" + kfsmc + '\'' +
                ", dchs:'" + dchs + '\'' +
                ", gchs:'" + gchs + '\'' +
                ", lxr:'" + lxr + '\'' +
                ", lxrsjh:'" + lxrsjh + '\'' +
                ", slr:'" + slr + '\'' +
                ", slrq:'" + sdft.format(slrq) + '\'' +
                ", bgr:'" + bgr + '\'' +
                ", bgsj:'" + bgsj + '\'' +
                ", bgnr:'" + bgnr + '\'' +
                ", bz:'" + bz + '\'' +
                ", xzqybh:'" + xzqybh + '\'' +
                ", fpzt:'" + fpzt + '\'' +
                ", fpr:'" + fpr + '\'' +
                ", fpsj:'" + fpsj + '\'' +
                ", xmjl:'" + xmjl + '\'' +
                ", xmdz_sheng:'" + xmdz_sheng + '\'' +
                ", xmdz_sShi:'" + xmdz_shi + '\'' +
                ", xmdz_xzqybh:'" + xmdz_xzqybh + '\'' +
                ", xmdz:'" + xmdz + '\'' +
                ", azlx:'" + azlx + '\'' +
                ", zjzmj:'" + zjzmj + '\'' +
                ", zzjzmj:'" + zzjzmj + '\'' +
                ", zgcs:'" + zgcs + '\'' +
                ", sqly:'" + sqly + '\'' +
                ", yhhh:'" + yhhh + '\'' +
                ", yhhm:'" + yhhm + '\'' +
                ", sbkj:'" + sbkj + '\'' +
                ", jsyt:'" + jsyt + '\'' +
                ", sblx:'" + sblx + '\'' +
                ", yhh:'" + yhh + '\'' +
                ", ybkj:'" + ybkj + '\'' +
                ", dwmc:'" + dwmc + '\'' +
                ", yhxz:'" + yhxz + '\'' +
                ", dkjc:'" + dkjc + '\'' +
                ", hs:'" + hs + '\'' +
                ", sjfw:'" + sjfw + '\'' +
                ", dylh:'" + dylh + '\'' +
                ", ghbfs:'" + ghbfs + '\'' +
                ", jfsj:'" + sdft.format(jfsj) + '\'' +
                ", fqqk:'" + fqqk + '\'' +
                ", sflx:'" + sflx + '\'' +
                ", zjlxdh:'" + zjlxdh + '\'' +
                '}';
    }
}
