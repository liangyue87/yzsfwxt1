package com.ljx.hfgsjt.entity.dto.yswt;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "YS_YSJEZH")

public class YsYsjezh {
  @Id
  private String id;
  private String ysrwzbid;
  private String mc;
  private String ysje;
  private String bz;


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


  public String getMc() {
    return mc;
  }

  public void setMc(String mc) {
    this.mc = mc;
  }


  public String getYsje() {
    return ysje;
  }

  public void setYsje(String ysje) {
    this.ysje = ysje;
  }


  public String getBz() {
    return bz;
  }

  public void setBz(String bz) {
    this.bz = bz;
  }

}
