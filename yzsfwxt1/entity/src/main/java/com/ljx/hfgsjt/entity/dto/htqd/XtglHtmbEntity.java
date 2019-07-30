package com.ljx.hfgsjt.entity.dto.htqd;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Clob;

@Entity
@Table(name = "XTGL_HTMB")
public class XtglHtmbEntity {

  @Id
  @Column(name = "id")
  private String wybs;
  private String mbbh;
  private String mbmc;
  private String mblx;
  private String mbbm;
  private Clob mbnr;
  private String cjr;
  private java.util.Date cjsj;
  private String xgr;
  private java.util.Date xgsj;
  @Column(name = "sc_flag")
  private String scFlag;


  public String getWybs() {
    return wybs;
  }

  public void setWybs(String wybs) {
    this.wybs = wybs;
  }


  public String getMbbh() {
    return mbbh;
  }

  public void setMbbh(String mbbh) {
    this.mbbh = mbbh;
  }


  public String getMbmc() {
    return mbmc;
  }

  public void setMbmc(String mbmc) {
    this.mbmc = mbmc;
  }


  public String getMblx() {
    return mblx;
  }

  public void setMblx(String mblx) {
    this.mblx = mblx;
  }


  public String getMbbm() {
    return mbbm;
  }

  public void setMbbm(String mbbm) {
    this.mbbm = mbbm;
  }


  public Clob getMbnr() {
    return mbnr;
  }

  public void setMbnr(Clob mbnr) {
    this.mbnr = mbnr;
  }


  public String getCjr() {
    return cjr;
  }

  public void setCjr(String cjr) {
    this.cjr = cjr;
  }


  public java.util.Date getCjsj() {
    return cjsj;
  }

  public void setCjsj(java.util.Date cjsj) {
    this.cjsj = cjsj;
  }


  public String getXgr() {
    return xgr;
  }

  public void setXgr(String xgr) {
    this.xgr = xgr;
  }


  public java.util.Date getXgsj() {
    return xgsj;
  }

  public void setXgsj(java.util.Date xgsj) {
    this.xgsj = xgsj;
  }


  public String getScFlag() {
    return scFlag;
  }

  public void setScFlag(String scFlag) {
    this.scFlag = scFlag;
  }

}
