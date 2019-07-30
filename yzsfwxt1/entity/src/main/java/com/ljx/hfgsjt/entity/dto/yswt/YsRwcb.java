package com.ljx.hfgsjt.entity.dto.yswt;

import javax.persistence.*;

@Entity
@Table(name = "YS_RWCB")
public class YsRwcb {
  @Id
  private String id;
  private String ysrwzbid;
  private String ysfl;
  private String ysje;
  @Column(name = "ysje_old")
  private String ysjeOld;
  private String bz;

  @Transient
  private String mc;


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


  public String getYsfl() {
    return ysfl;
  }

  public void setYsfl(String ysfl) {
    this.ysfl = ysfl;
  }


  public String getYsje() {
    return ysje;
  }

  public void setYsje(String ysje) {
    this.ysje = ysje;
  }


  public String getYsjeOld() {
    return ysjeOld;
  }

  public void setYsjeOld(String ysjeOld) {
    this.ysjeOld = ysjeOld;
  }


  public String getBz() {
    return bz;
  }

  public void setBz(String bz) {
    this.bz = bz;
  }


  public String getMc() {
    return mc;
  }

  public void setMc(String mc) {
    this.mc = mc;
  }
}
