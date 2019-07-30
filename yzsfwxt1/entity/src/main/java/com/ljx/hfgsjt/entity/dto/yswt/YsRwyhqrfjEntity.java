package com.ljx.hfgsjt.entity.dto.yswt;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "YS_RWYHQRFJ")
public class YsRwyhqrfjEntity {

  @Id
  private String id;
  private String ysrwzbid;
  private String fjlxYj;
  private String fjlxEj;
  private String fjlj;
  private String fjhz;
  private String fjmc;
  private String scr;
  private java.sql.Date scsj;
  private String scFlag;
  private String shcr;
  private java.sql.Date shcsj;


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public String getYsrwzbid() {
    return ysrwzbid;
  }

  public void setYsrwzbid(String ysrwzbid) {
    this.ysrwzbid = ysrwzbid;
  }


  public String getFjlxYj() {
    return fjlxYj;
  }

  public void setFjlxYj(String fjlxYj) {
    this.fjlxYj = fjlxYj;
  }


  public String getFjlxEj() {
    return fjlxEj;
  }

  public void setFjlxEj(String fjlxEj) {
    this.fjlxEj = fjlxEj;
  }


  public String getFjlj() {
    return fjlj;
  }

  public void setFjlj(String fjlj) {
    this.fjlj = fjlj;
  }


  public String getFjhz() {
    return fjhz;
  }

  public void setFjhz(String fjhz) {
    this.fjhz = fjhz;
  }


  public String getFjmc() {
    return fjmc;
  }

  public void setFjmc(String fjmc) {
    this.fjmc = fjmc;
  }


  public String getScr() {
    return scr;
  }

  public void setScr(String scr) {
    this.scr = scr;
  }


  public java.sql.Date getScsj() {
    return scsj;
  }

  public void setScsj(java.sql.Date scsj) {
    this.scsj = scsj;
  }


  public String getScFlag() {
    return scFlag;
  }

  public void setScFlag(String scFlag) {
    this.scFlag = scFlag;
  }


  public String getShcr() {
    return shcr;
  }

  public void setShcr(String shcr) {
    this.shcr = shcr;
  }


  public java.sql.Date getShcsj() {
    return shcsj;
  }

  public void setShcsj(java.sql.Date shcsj) {
    this.shcsj = shcsj;
  }

}
