package com.ljx.hfgsjt.entity.xqsl;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "XQSL_YZB")
public class XmYzbEntity {

  private String id;
  private String sqid;
  private String xmid;
  private String hh;
  private String hm;
  private String dz;
  private String kjbh;
  private String ysxzbh;
  private String hyflbh;
  private String kchs;
  private String bz;
  private String qsbh;
  private String kjmc;
  private String ysxz;
  private String hyfl;

  public String getSqid() {
    return sqid;
  }

  public void setSqid(String sqid) {
    this.sqid = sqid;
  }

  public String getKjmc() {
    return kjmc;
  }

  public void setKjmc(String kjmc) {
    this.kjmc = kjmc;
  }

  public String getYsxz() {
    return ysxz;
  }

  public void setYsxz(String ysxz) {
    this.ysxz = ysxz;
  }

  public String getHyfl() {
    return hyfl;
  }

  public void setHyfl(String hyfl) {
    this.hyfl = hyfl;
  }

  @Id
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public String getXmid() {
    return xmid;
  }

  public void setXmid(String xmid) {
    this.xmid = xmid;
  }


  public String getHh() {
    return hh;
  }

  public void setHh(String hh) {
    this.hh = hh;
  }


  public String getHm() {
    return hm;
  }

  public void setHm(String hm) {
    this.hm = hm;
  }


  public String getDz() {
    return dz;
  }

  public void setDz(String dz) {
    this.dz = dz;
  }


  public String getKjbh() {
    return kjbh;
  }

  public void setKjbh(String kjbh) {
    this.kjbh = kjbh;
  }


  public String getYsxzbh() {
    return ysxzbh;
  }

  public void setYsxzbh(String ysxzbh) {
    this.ysxzbh = ysxzbh;
  }


  public String getHyflbh() {
    return hyflbh;
  }

  public void setHyflbh(String hyflbh) {
    this.hyflbh = hyflbh;
  }


  public String getKchs() {
    return kchs;
  }

  public void setKchs(String kchs) {
    this.kchs = kchs;
  }


  public String getBz() {
    return bz;
  }

  public void setBz(String bz) {
    this.bz = bz;
  }


  public String getQsbh() {
    return qsbh;
  }

  public void setQsbh(String qsbh) {
    this.qsbh = qsbh;
  }

}
