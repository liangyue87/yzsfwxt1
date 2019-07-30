package com.ljx.hfgsjt.entity.yj;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "YJ_BWYJZB")
public class YjBwyjzb {
  @Id
  private String id;
  private String bwyjdbh;
  private String xmid;
  private String bwyjdzt;
  private String jdr;
  @Transient
  private String jdsj_vue;
  private java.util.Date jdsj;
  private String jdbm;
  private String yjfsbh;
  private java.util.Date yysj;
  @Transient
  private String yysj_vue;
  private String qsysry;
  private String qsrysj;
  private String cjr;
  private java.util.Date cjsj;
  @Transient
  private String cjsj_vue;
  private String dzr;
  private java.util.Date dzsj;
  @Transient
  private String dzsj_vue;
  private String dzbz;

  public String getJdsj_vue() {
    return jdsj_vue;
  }

  public void setJdsj_vue(String jdsj_vue) {
    this.jdsj_vue = jdsj_vue;
  }

  public String getYysj_vue() {
    return yysj_vue;
  }

  public void setYysj_vue(String yysj_vue) {
    this.yysj_vue = yysj_vue;
  }

  public String getCjsj_vue() {
    return cjsj_vue;
  }

  public void setCjsj_vue(String cjsj_vue) {
    this.cjsj_vue = cjsj_vue;
  }

  public String getDzsj_vue() {
    return dzsj_vue;
  }

  public void setDzsj_vue(String dzsj_vue) {
    this.dzsj_vue = dzsj_vue;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public String getBwyjdbh() {
    return bwyjdbh;
  }

  public void setBwyjdbh(String bwyjdbh) {
    this.bwyjdbh = bwyjdbh;
  }


  public String getXmid() {
    return xmid;
  }

  public void setXmid(String xmid) {
    this.xmid = xmid;
  }


  public String getBwyjdzt() {
    return bwyjdzt;
  }

  public void setBwyjdzt(String bwyjdzt) {
    this.bwyjdzt = bwyjdzt;
  }


  public String getJdr() {
    return jdr;
  }

  public void setJdr(String jdr) {
    this.jdr = jdr;
  }


  public java.util.Date getJdsj() {
    return jdsj;
  }

  public void setJdsj(java.util.Date jdsj) {
    this.jdsj = jdsj;
  }


  public String getJdbm() {
    return jdbm;
  }

  public void setJdbm(String jdbm) {
    this.jdbm = jdbm;
  }


  public String getYjfsbh() {
    return yjfsbh;
  }

  public void setYjfsbh(String yjfsbh) {
    this.yjfsbh = yjfsbh;
  }


  public java.util.Date getYysj() {
    return yysj;
  }

  public void setYysj(java.util.Date yysj) {
    this.yysj = yysj;
  }


  public String getQsysry() {
    return qsysry;
  }

  public void setQsysry(String qsysry) {
    this.qsysry = qsysry;
  }


  public String getQsrysj() {
    return qsrysj;
  }

  public void setQsrysj(String qsrysj) {
    this.qsrysj = qsrysj;
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


  public String getDzr() {
    return dzr;
  }

  public void setDzr(String dzr) {
    this.dzr = dzr;
  }


  public java.util.Date getDzsj() {
    return dzsj;
  }

  public void setDzsj(java.util.Date dzsj) {
    this.dzsj = dzsj;
  }


  public String getDzbz() {
    return dzbz;
  }

  public void setDzbz(String dzbz) {
    this.dzbz = dzbz;
  }

}
