package com.ljx.hfgsjt.entity.xqsl;


import javax.persistence.*;

@Entity
@Table(name = "YWSL_XJXQSQ_LDXX")
public class YwslXjxqsqLdxxEntity {

  private String id;
  private String xmid;
  private String sqid;
  private String ldh;
  private String dys;
  private String lc;
  private String mchs;
  private String zhs;
  private String bz;
  private String sqlx;

  @Basic
  @Column(name = "SQLX")
  public String getSqlx() {
    return sqlx;
  }

  public void setSqlx(String sqlx) {
    this.sqlx = sqlx;
  }

  @Id
  @Column(name = "ID")
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
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
  @Column(name = "SQID")
  public String getSqid() {
    return sqid;
  }

  public void setSqid(String sqid) {
    this.sqid = sqid;
  }

  @Basic
  @Column(name = "LDH")
  public String getLdh() {
    return ldh;
  }

  public void setLdh(String ldh) {
    this.ldh = ldh;
  }

  @Basic
  @Column(name = "DYS")
  public String getDys() {
    return dys;
  }

  public void setDys(String dys) {
    this.dys = dys;
  }

  @Basic
  @Column(name = "LC")
  public String getLc() {
    return lc;
  }

  public void setLc(String lc) {
    this.lc = lc;
  }


  @Basic
  @Column(name = "MCHS")
  public String getMchs() {
    return mchs;
  }

  public void setMchs(String mchs) {
    this.mchs = mchs;
  }

  @Basic
  @Column(name = "ZHS")
  public String getZhs() {
    return zhs;
  }

  public void setZhs(String zhs) {
    this.zhs = zhs;
  }

  @Basic
  @Column(name = "BZ")
  public String getBz() {
    return bz;
  }

  public void setBz(String bz) {
    this.bz = bz;
  }

}
