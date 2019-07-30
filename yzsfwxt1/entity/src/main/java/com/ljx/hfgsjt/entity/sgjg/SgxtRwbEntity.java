package com.ljx.hfgsjt.entity.sgjg;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "SGXT_RWB")
public class SgxtRwbEntity {

  private String id;
  private String rwbh;
  private String xmid;
  private String fqr;
  private java.util.Date fqrq;
  private String gcnrTy;
  private String gcnrBf;
  private String gcnrLg;
  private String gcnrDg;
  private String gcnrQt;
  private String xmgzry;
  private java.util.Date rwclsj;
  private String zt;
  private String bz;
  private String thyy;
  private String shzt;
  private java.util.Date jhjgsj;//计划竣工时间

  @Basic
  @Column(name = "SHZT")
  public String getShzt() {
    return shzt;
  }

  public void setShzt(String shzt) {
    this.shzt = shzt;
  }

  @Basic
  @Column(name = "THYY")
  public String getThyy() {
    return thyy;
  }

  public void setThyy(String thyy) {
    this.thyy = thyy;
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
  @Column(name = "RWBH")
  public String getRwbh() {
    return rwbh;
  }

  public void setRwbh(String rwbh) {
    this.rwbh = rwbh;
  }

  @Basic
  @Column(name = "XMID")
  public String getXmid() {
    return xmid;
  }

  public void setXmid(String xmid) {
    this.xmid = xmid;
  }

  @Basic
  @Column(name = "FQR")
  public String getFqr() {
    return fqr;
  }

  public void setFqr(String fqr) {
    this.fqr = fqr;
  }

  @Basic
  @Column(name = "FQRQ")
  public java.util.Date getFqrq() {
    return fqrq;
  }

  public void setFqrq(java.util.Date fqrq) {
    this.fqrq = fqrq;
  }

  @Basic
  @Column(name = "GCNR_TY")
  public String getGcnrTy() {
    return gcnrTy;
  }

  public void setGcnrTy(String gcnrTy) {
    this.gcnrTy = gcnrTy;
  }

  @Basic
  @Column(name = "GCNR_BF")
  public String getGcnrBf() {
    return gcnrBf;
  }

  public void setGcnrBf(String gcnrBf) {
    this.gcnrBf = gcnrBf;
  }

  @Basic
  @Column(name = "GCNR_LG")
  public String getGcnrLg() {
    return gcnrLg;
  }

  public void setGcnrLg(String gcnrLg) {
    this.gcnrLg = gcnrLg;
  }

  @Basic
  @Column(name = "GCNR_DG")
  public String getGcnrDg() {
    return gcnrDg;
  }

  public void setGcnrDg(String gcnrDg) {
    this.gcnrDg = gcnrDg;
  }

  @Basic
  @Column(name = "GCNR_QT")
  public String getGcnrQt() {
    return gcnrQt;
  }

  public void setGcnrQt(String gcnrQt) {
    this.gcnrQt = gcnrQt;
  }

  @Basic
  @Column(name = "XMGZRY")
  public String getXmgzry() {
    return xmgzry;
  }

  public void setXmgzry(String xmgzry) {
    this.xmgzry = xmgzry;
  }

  @Basic
  @Column(name = "RWCLSJ")
  public java.util.Date getRwclsj() {
    return rwclsj;
  }

  public void setRwclsj(java.util.Date rwclsj) {
    this.rwclsj = rwclsj;
  }

  @Basic
  @Column(name = "ZT")
  public String getZt() {
    return zt;
  }

  public void setZt(String zt) {
    this.zt = zt;
  }

  @Basic
  @Column(name = "BZ")
  public String getBz() {
    return bz;
  }

  public void setBz(String bz) {
    this.bz = bz;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    SgxtRwbEntity that = (SgxtRwbEntity) o;
    return Objects.equals(id, that.id) &&
            Objects.equals(rwbh, that.rwbh) &&
            Objects.equals(xmid, that.xmid) &&
            Objects.equals(fqr, that.fqr) &&
            Objects.equals(fqrq, that.fqrq) &&
            Objects.equals(gcnrTy, that.gcnrTy) &&
            Objects.equals(gcnrBf, that.gcnrBf) &&
            Objects.equals(gcnrLg, that.gcnrLg) &&
            Objects.equals(gcnrDg, that.gcnrDg) &&
            Objects.equals(gcnrQt, that.gcnrQt) &&
            Objects.equals(xmgzry, that.xmgzry) &&
            Objects.equals(rwclsj, that.rwclsj) &&
            Objects.equals(zt, that.zt) &&
            Objects.equals(bz, that.bz) &&
            Objects.equals(thyy, that.thyy) &&
            Objects.equals(shzt, that.shzt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, rwbh, xmid, fqr, fqrq, gcnrTy, gcnrBf, gcnrLg, gcnrDg, gcnrQt, xmgzry, rwclsj, zt, bz, thyy, shzt);
  }

  public Date getJhjgsj() {
    return jhjgsj;
  }

  public void setJhjgsj(Date jhjgsj) {
    this.jhjgsj = jhjgsj;
  }
}
