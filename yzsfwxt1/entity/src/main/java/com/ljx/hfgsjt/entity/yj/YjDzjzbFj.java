package com.ljx.hfgsjt.entity.yj;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


@Entity
@Table(name = "YJ_DZJZB_FJ")
public class YjDzjzbFj {
  @Id
  private String id;
  private String dzjid;
  private String fjlx_yj;
  private String fjlx_ej;
  private String fjlj;
  private String fjhz;
  private String fjmc;
  private String scr;
  private java.util.Date scsj;
  private String sc_flag;
  private String shcr;
  private String shcsj;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getDzjid() {
    return dzjid;
  }

  public void setDzjid(String dzjid) {
    this.dzjid = dzjid;
  }

  public String getFjlx_yj() {
    return fjlx_yj;
  }

  public void setFjlx_yj(String fjlx_yj) {
    this.fjlx_yj = fjlx_yj;
  }

  public String getFjlx_ej() {
    return fjlx_ej;
  }

  public void setFjlx_ej(String fjlx_ej) {
    this.fjlx_ej = fjlx_ej;
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

  public java.util.Date getScsj() {
    return scsj;
  }

  public void setScsj(Date scsj) {
    this.scsj = scsj;
  }

  public String getSc_flag() {
    return sc_flag;
  }

  public void setSc_flag(String sc_flag) {
    this.sc_flag = sc_flag;
  }

  public String getShcr() {
    return shcr;
  }

  public void setShcr(String shcr) {
    this.shcr = shcr;
  }

  public String getShcsj() {
    return shcsj;
  }

  public void setShcsj(String shcsj) {
    this.shcsj = shcsj;
  }
}
