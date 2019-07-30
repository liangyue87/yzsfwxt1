package com.ljx.hfgsjt.entity.yj;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "YJ_DZJCB")
public class YjDzjcb {
  @Id
  private String id;
  private String dzjzbid;
  private String dzjcblxbh;
  private String fbjj;
  private String fmid;
  private String xhsid;
  private String sbid;
  private String gdkjbh;
  private String gdczbh;
  private String ldid;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getDzjzbid() {
    return dzjzbid;
  }

  public void setDzjzbid(String dzjzbid) {
    this.dzjzbid = dzjzbid;
  }

  public String getDzjcblxbh() {
    return dzjcblxbh;
  }

  public void setDzjcblxbh(String dzjcblxbh) {
    this.dzjcblxbh = dzjcblxbh;
  }

  public String getFbjj() {
    return fbjj;
  }

  public void setFbjj(String fbjj) {
    this.fbjj = fbjj;
  }

  public String getFmid() {
    return fmid;
  }

  public void setFmid(String fmid) {
    this.fmid = fmid;
  }

  public String getXhsid() {
    return xhsid;
  }

  public void setXhsid(String xhsid) {
    this.xhsid = xhsid;
  }

  public String getSbid() {
    return sbid;
  }

  public void setSbid(String sbid) {
    this.sbid = sbid;
  }

  public String getGdkjbh() {
    return gdkjbh;
  }

  public void setGdkjbh(String gdkjbh) {
    this.gdkjbh = gdkjbh;
  }

  public String getGdczbh() {
    return gdczbh;
  }

  public void setGdczbh(String gdczbh) {
    this.gdczbh = gdczbh;
  }

  public String getLdid() {
    return ldid;
  }

  public void setLdid(String ldid) {
    this.ldid = ldid;
  }
}
