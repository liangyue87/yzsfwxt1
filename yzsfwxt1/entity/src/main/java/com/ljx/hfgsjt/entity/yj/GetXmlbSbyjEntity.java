package com.ljx.hfgsjt.entity.yj;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "GET_XMLB_SBYJ")
public class GetXmlbSbyjEntity {
    @Id
    private String id;
    private String pxmbh;
    private String xmbh;
    private String sqlx;
    private String azlx;
    private String slid;
    private String sqbh;
    private String xmlb;
    private String xmtzf;
    private String xmswlxfl;
    private String qbid;
    private String status;
    private String slrbh;
    private String lxsj;
    private String xmfzr;
    private String xmmc;
    private String lxr;
    private String lxrsjh;
    private String xmdz;
    private String dwmc;
    private String slsj;
    private long iszj;
    private String lxdh;
    private String xzqybh;
    private Long zjzmj;
    private String sjfzr;
    private String stfzr;
    private String jlfzr;
    private String zjfzr;
    private String ysfzr;
    private String qsfzr;
    private String sgfzr;
    private String bz;
    private String bmbmQs;
    private String jhkssj;
    private String jhjssj;
    private String xmdzSheng;
    private String xmdzShi;
    private String xmdzXzqybh;
    private String slsjStr;
    private String first;
    private String procStatus;
    private String pdefId;
    private String sjyssj;
    private String azlxname;

    @Basic
    @Column(name = "AZLXNAME")
    public String getAzlxname() {
        return azlxname;
    }

    public void setAzlxname(String azlxname) {
        this.azlxname = azlxname;
    }

    @Basic
    @Column(name = "SJYSSJ")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    public String getSjyssj() {
        return sjyssj;
    }

    public void setSjyssj(String sjyssj) {
        this.sjyssj = sjyssj;
    }

    @Basic
    @Column(name = "ID")
    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "PXMBH")
    public String getPxmbh() {
        return pxmbh;
    }

    public void setPxmbh(String pxmbh) {
        this.pxmbh = pxmbh;
    }

    @Basic
    @Column(name = "XMBH")
    public String getXmbh() {
        return xmbh;
    }

    public void setXmbh(String xmbh) {
        this.xmbh = xmbh;
    }

    @Basic
    @Column(name = "SQLX")
    public String getSqlx() {
        return sqlx;
    }

    public void setSqlx(String sqlx) {
        this.sqlx = sqlx;
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
    @Column(name = "SLID")
    public String getSlid() {
        return slid;
    }

    public void setSlid(String slid) {
        this.slid = slid;
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
    @Column(name = "XMLB")
    public String getXmlb() {
        return xmlb;
    }

    public void setXmlb(String xmlb) {
        this.xmlb = xmlb;
    }

    @Basic
    @Column(name = "XMTZF")
    public String getXmtzf() {
        return xmtzf;
    }

    public void setXmtzf(String xmtzf) {
        this.xmtzf = xmtzf;
    }

    @Basic
    @Column(name = "XMSWLXFL")
    public String getXmswlxfl() {
        return xmswlxfl;
    }

    public void setXmswlxfl(String xmswlxfl) {
        this.xmswlxfl = xmswlxfl;
    }

    @Basic
    @Column(name = "QBID")
    public String getQbid() {
        return qbid;
    }

    public void setQbid(String qbid) {
        this.qbid = qbid;
    }

    @Basic
    @Column(name = "STATUS")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "SLRBH")
    public String getSlrbh() {
        return slrbh;
    }

    public void setSlrbh(String slrbh) {
        this.slrbh = slrbh;
    }

    @Basic
    @Column(name = "LXSJ")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public String getLxsj() {
        return lxsj;
    }

    public void setLxsj(String lxsj) {
        this.lxsj = lxsj;
    }

    @Basic
    @Column(name = "XMFZR")
    public String getXmfzr() {
        return xmfzr;
    }

    public void setXmfzr(String xmfzr) {
        this.xmfzr = xmfzr;
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
    @Column(name = "XMDZ")
    public String getXmdz() {
        return xmdz;
    }

    public void setXmdz(String xmdz) {
        this.xmdz = xmdz;
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
    @Column(name = "SLSJ")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public String getSlsj() {
        return slsj;
    }

    public void setSlsj(String slsj) {
        this.slsj = slsj;
    }

    @Basic
    @Column(name = "ISZJ")
    public long getIszj() {
        return iszj;
    }

    public void setIszj(long iszj) {
        this.iszj = iszj;
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
    @Column(name = "ZJZMJ")
    public Long getZjzmj() {
        return zjzmj;
    }

    public void setZjzmj(Long zjzmj) {
        this.zjzmj = zjzmj;
    }

    @Basic
    @Column(name = "SJFZR")
    public String getSjfzr() {
        return sjfzr;
    }

    public void setSjfzr(String sjfzr) {
        this.sjfzr = sjfzr;
    }

    @Basic
    @Column(name = "STFZR")
    public String getStfzr() {
        return stfzr;
    }

    public void setStfzr(String stfzr) {
        this.stfzr = stfzr;
    }

    @Basic
    @Column(name = "JLFZR")
    public String getJlfzr() {
        return jlfzr;
    }

    public void setJlfzr(String jlfzr) {
        this.jlfzr = jlfzr;
    }

    @Basic
    @Column(name = "ZJFZR")
    public String getZjfzr() {
        return zjfzr;
    }

    public void setZjfzr(String zjfzr) {
        this.zjfzr = zjfzr;
    }

    @Basic
    @Column(name = "YSFZR")
    public String getYsfzr() {
        return ysfzr;
    }

    public void setYsfzr(String ysfzr) {
        this.ysfzr = ysfzr;
    }

    @Basic
    @Column(name = "QSFZR")
    public String getQsfzr() {
        return qsfzr;
    }

    public void setQsfzr(String qsfzr) {
        this.qsfzr = qsfzr;
    }

    @Basic
    @Column(name = "SGFZR")
    public String getSgfzr() {
        return sgfzr;
    }

    public void setSgfzr(String sgfzr) {
        this.sgfzr = sgfzr;
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
    @Column(name = "BMBM_QS")
    public String getBmbmQs() {
        return bmbmQs;
    }

    public void setBmbmQs(String bmbmQs) {
        this.bmbmQs = bmbmQs;
    }

    @Basic
    @Column(name = "JHKSSJ")
    public String getJhkssj() {
        return jhkssj;
    }

    public void setJhkssj(String jhkssj) {
        this.jhkssj = jhkssj;
    }

    @Basic
    @Column(name = "JHJSSJ")
    public String getJhjssj() {
        return jhjssj;
    }

    public void setJhjssj(String jhjssj) {
        this.jhjssj = jhjssj;
    }

    @Basic
    @Column(name = "XMDZ_SHENG")
    public String getXmdzSheng() {
        return xmdzSheng;
    }

    public void setXmdzSheng(String xmdzSheng) {
        this.xmdzSheng = xmdzSheng;
    }

    @Basic
    @Column(name = "XMDZ_SHI")
    public String getXmdzShi() {
        return xmdzShi;
    }

    public void setXmdzShi(String xmdzShi) {
        this.xmdzShi = xmdzShi;
    }

    @Basic
    @Column(name = "XMDZ_XZQYBH")
    public String getXmdzXzqybh() {
        return xmdzXzqybh;
    }

    public void setXmdzXzqybh(String xmdzXzqybh) {
        this.xmdzXzqybh = xmdzXzqybh;
    }

    @Basic
    @Column(name = "SLSJ_STR")
    public String getSlsjStr() {
        return slsjStr;
    }

    public void setSlsjStr(String slsjStr) {
        this.slsjStr = slsjStr;
    }

    @Basic
    @Column(name = "FIRST")
    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    @Basic
    @Column(name = "PROC_STATUS")
    public String getProcStatus() {
        return procStatus;
    }

    public void setProcStatus(String procStatus) {
        this.procStatus = procStatus;
    }

    @Basic
    @Column(name = "PDEF_ID")
    public String getPdefId() {
        return pdefId;
    }

    public void setPdefId(String pdefId) {
        this.pdefId = pdefId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetXmlbSbyjEntity that = (GetXmlbSbyjEntity) o;
        return iszj == that.iszj &&
                Objects.equals(id, that.id) &&
                Objects.equals(pxmbh, that.pxmbh) &&
                Objects.equals(xmbh, that.xmbh) &&
                Objects.equals(sqlx, that.sqlx) &&
                Objects.equals(azlx, that.azlx) &&
                Objects.equals(slid, that.slid) &&
                Objects.equals(sqbh, that.sqbh) &&
                Objects.equals(xmlb, that.xmlb) &&
                Objects.equals(xmtzf, that.xmtzf) &&
                Objects.equals(xmswlxfl, that.xmswlxfl) &&
                Objects.equals(qbid, that.qbid) &&
                Objects.equals(status, that.status) &&
                Objects.equals(slrbh, that.slrbh) &&
                Objects.equals(lxsj, that.lxsj) &&
                Objects.equals(xmfzr, that.xmfzr) &&
                Objects.equals(xmmc, that.xmmc) &&
                Objects.equals(lxr, that.lxr) &&
                Objects.equals(lxrsjh, that.lxrsjh) &&
                Objects.equals(xmdz, that.xmdz) &&
                Objects.equals(dwmc, that.dwmc) &&
                Objects.equals(slsj, that.slsj) &&
                Objects.equals(lxdh, that.lxdh) &&
                Objects.equals(xzqybh, that.xzqybh) &&
                Objects.equals(zjzmj, that.zjzmj) &&
                Objects.equals(sjfzr, that.sjfzr) &&
                Objects.equals(stfzr, that.stfzr) &&
                Objects.equals(jlfzr, that.jlfzr) &&
                Objects.equals(zjfzr, that.zjfzr) &&
                Objects.equals(ysfzr, that.ysfzr) &&
                Objects.equals(qsfzr, that.qsfzr) &&
                Objects.equals(sgfzr, that.sgfzr) &&
                Objects.equals(bz, that.bz) &&
                Objects.equals(bmbmQs, that.bmbmQs) &&
                Objects.equals(jhkssj, that.jhkssj) &&
                Objects.equals(jhjssj, that.jhjssj) &&
                Objects.equals(xmdzSheng, that.xmdzSheng) &&
                Objects.equals(xmdzShi, that.xmdzShi) &&
                Objects.equals(xmdzXzqybh, that.xmdzXzqybh) &&
                Objects.equals(slsjStr, that.slsjStr) &&
                Objects.equals(first, that.first) &&
                Objects.equals(procStatus, that.procStatus) &&
                Objects.equals(pdefId, that.pdefId) &&
                Objects.equals(sjyssj, that.sjyssj) &&
                Objects.equals(azlxname, that.azlxname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pxmbh, xmbh, sqlx, azlx, slid, sqbh, xmlb, xmtzf, xmswlxfl, qbid, status, slrbh, lxsj, xmfzr, xmmc, lxr, lxrsjh, xmdz, dwmc, slsj, iszj, lxdh, xzqybh, zjzmj, sjfzr, stfzr, jlfzr, zjfzr, ysfzr, qsfzr, sgfzr, bz, bmbmQs, jhkssj, jhjssj, xmdzSheng, xmdzShi, xmdzXzqybh, slsjStr, first, procStatus, pdefId, sjyssj, azlxname);
    }
}
