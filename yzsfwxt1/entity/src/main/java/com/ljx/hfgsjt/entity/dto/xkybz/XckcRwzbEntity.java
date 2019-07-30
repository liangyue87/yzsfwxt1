package com.ljx.hfgsjt.entity.dto.xkybz;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "XCKC_RWZB")
public class XckcRwzbEntity {
  @Id
  private String id;
  private String sqlb;
  private String sqid;
  private String fqr;
  private java.util.Date fqrq;
  private String dcbjb;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
  private java.util.Date yykcrq;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
  private Date sjdcrq;
  private String kczt;
  private String xmjbqk;
  private String bz;
  private java.util.Date gxsj;
  private String xmjlpj;
  private java.util.Date pjsj;
  private String khsfty;
  private java.util.Date khtjsj;
  private String dcdjb;
  private String lxdh;
  private String lxr;
  private String xmid;
  private String rwbh;
  private String kcdd;


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public String getSqlb() {
    return sqlb;
  }

  public void setSqlb(String sqlb) {
    this.sqlb = sqlb;
  }


  public String getSqid() {
    return sqid;
  }

  public void setSqid(String sqid) {
    this.sqid = sqid;
  }


  public String getFqr() {
    return fqr;
  }

  public void setFqr(String fqr) {
    this.fqr = fqr;
  }


  public java.util.Date getFqrq() {
    return fqrq;
  }

  public void setFqrq(java.util.Date fqrq) {
    this.fqrq = fqrq;
  }


  public String getDcbjb() {
    return dcbjb;
  }

  public void setDcbjb(String dcbjb) {
    this.dcbjb = dcbjb;
  }


  public java.util.Date getYykcrq() {
    return yykcrq;
  }

  public void setYykcrq(java.util.Date yykcrq) {
    this.yykcrq = yykcrq;
  }


  public Date getSjdcrq() {
    return sjdcrq;
  }

  public void setSjdcrq(Date sjdcrq) {
    this.sjdcrq = sjdcrq;
  }


  public String getKczt() {
    return kczt;
  }

  public void setKczt(String kczt) {
    this.kczt = kczt;
  }


  public String getXmjbqk() {
    return xmjbqk;
  }

  public void setXmjbqk(String xmjbqk) {
    this.xmjbqk = xmjbqk;
  }


  public String getBz() {
    return bz;
  }

  public void setBz(String bz) {
    this.bz = bz;
  }


  public java.util.Date getGxsj() {
    return gxsj;
  }

  public void setGxsj(java.util.Date gxsj) {
    this.gxsj = gxsj;
  }


  public String getXmjlpj() {
    return xmjlpj;
  }

  public void setXmjlpj(String xmjlpj) {
    this.xmjlpj = xmjlpj;
  }


  public java.util.Date getPjsj() {
    return pjsj;
  }

  public void setPjsj(java.util.Date pjsj) {
    this.pjsj = pjsj;
  }


  public String getKhsfty() {
    return khsfty;
  }

  public void setKhsfty(String khsfty) {
    this.khsfty = khsfty;
  }


  public java.util.Date getKhtjsj() {
    return khtjsj;
  }

  public void setKhtjsj(java.util.Date khtjsj) {
    this.khtjsj = khtjsj;
  }


  public String getDcdjb() {
    return dcdjb;
  }

  public void setDcdjb(String dcdjb) {
    this.dcdjb = dcdjb;
  }


  public String getLxdh() {
    return lxdh;
  }

  public void setLxdh(String lxdh) {
    this.lxdh = lxdh;
  }


  public String getLxr() {
    return lxr;
  }

  public void setLxr(String lxr) {
    this.lxr = lxr;
  }


  public String getXmid() {
    return xmid;
  }

  public void setXmid(String xmid) {
    this.xmid = xmid;
  }

  public String getKcdd() {
    return kcdd;
  }

  public void setKcdd(String kcdd) {
    this.kcdd = kcdd;
  }

  @Override
  public String toString() {
    SimpleDateFormat sdft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    return "{" +
            "id:'" + id + '\'' +
            ", sqlb:'" + sqlb + '\'' +
            ", sqid:'" + sqid + '\'' +
            ", fqr:'" + fqr + '\'' +
            ", fqrq:'" + fqrq + '\'' +
            ", dcbjb:'" + dcbjb + '\'' +
            ", yykcrq:'" + sdft.format(yykcrq) + '\'' +
            ", sjdcrq:'" + sjdcrq + '\'' +
            ", kczt:'" + kczt + '\'' +
            ", xmjbqk:'" + xmjbqk + '\'' +
            ", bz:'" + bz + '\'' +
            ", gxsj:'" + gxsj + '\'' +
            ", xmjlpj:'" + xmjlpj + '\'' +
            ", pjsj:'" + pjsj + '\'' +
            ", khsfty:'" + khsfty + '\'' +
            ", khtjsj:'" + khtjsj + '\'' +
            ", dcdjb:'" + dcdjb + '\'' +
            ", lxdh:'" + lxdh + '\'' +
            ", lxr:'" + lxr + '\'' +
            ", xmid:'" + xmid + '\'' +
            ", rwbh:'" + rwbh + '\'' +
            ", kcdd:'" + kcdd + '\'' +
            '}';
  }

  public void setRwbh(String rwbh) {
    this.rwbh = rwbh;
  }
  public String getRwbh() {
    return rwbh;
  }
}

