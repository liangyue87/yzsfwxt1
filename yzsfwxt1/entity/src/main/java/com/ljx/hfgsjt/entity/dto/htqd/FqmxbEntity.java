package com.ljx.hfgsjt.entity.dto.htqd;


import javax.persistence.Transient;

public class FqmxbEntity {

  private String id;
  private String fqzbid;
  private String fqfl;
  private String mc;
  private String fqje;
  private String fqperce;

  @Transient
  private String ysje;


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public String getFqzbid() {
    return fqzbid;
  }

  public void setFqzbid(String fqzbid) {
    this.fqzbid = fqzbid;
  }


  public String getFqfl() {
    return fqfl;
  }

  public void setFqfl(String fqfl) {
    this.fqfl = fqfl;
  }


  public String getMc() {
    return mc;
  }

  public void setMc(String mc) {
    this.mc = mc;
  }

  public String getFqje() {
    return fqje;
  }

  public void setFqje(String fqje) {
    this.fqje = fqje;
  }


  public String getFqperce() {
    return fqperce;
  }

  public void setFqperce(String fqperce) {
    this.fqperce = fqperce;
  }

  public String getYsje() {
    return ysje;
  }

  public void setYsje(String ysje) {
    this.ysje = ysje;
  }
}
