package com.ljx.hfgsjt.entity.dto.Sjst;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SJST_SXPZB")
public class SjstSxpzb {
  @Id
  private String id;
  private String pzmc;
  private double pzts;
  private String pz_type;
  private String pz_flag;
  private String cjr;
  private java.util.Date cjsj;


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public String getPzmc() {
    return pzmc;
  }

  public void setPzmc(String pzmc) {
    this.pzmc = pzmc;
  }


  public double getPzts() {
    return pzts;
  }

  public void setPzts(double pzts) {
    this.pzts = pzts;
  }

  public String getPz_type() {
    return pz_type;
  }

  public void setPz_type(String pz_type) {
    this.pz_type = pz_type;
  }

  public String getPz_flag() {
    return pz_flag;
  }

  public void setPz_flag(String pz_flag) {
    this.pz_flag = pz_flag;
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

}
