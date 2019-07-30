package com.ljx.hfgsjt.entity.dto.yswt;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Ys_Zlxx_NEW")
public class YsZlxx {
@Id
  private String fjid;
  private String yhqrfjid;//用户确认凭证上传表id
  private String yszbid;
  private String userid;
  private String fjmc;
  private String fjhz;
  private String  scsj;
  private String username;
  private String fjlj;//附件路径
  private String xmid;
  private String rwfjid;//任务附件id


  public String getFjid() {
    return fjid;
  }

  public void setFjid(String fjid) {
    this.fjid = fjid;
  }


  public String getYszbid() {
    return yszbid;
  }

  public void setYszbid(String yszbid) {
    this.yszbid = yszbid;
  }


  public String getUserid() {
    return userid;
  }

  public void setUserid(String userid) {
    this.userid = userid;
  }


  public String getFjmc() {
    return fjmc;
  }

  public void setFjmc(String fjmc) {
    this.fjmc = fjmc;
  }


  public String getFjhz() {
    return fjhz;
  }

  public void setFjhz(String fjhz) {
    this.fjhz = fjhz;
  }

  public String getScsj() {
    return scsj;
  }

  public void setScsj(String scsj) {
    this.scsj = scsj;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }


  public String getFjlj() {
    return fjlj;
  }

  public void setFjlj(String fjlj) {
    this.fjlj = fjlj;
  }

  public String getXmid() {
    return xmid;
  }

  public void setXmid(String xmid) {
    this.xmid = xmid;
  }

  public String getYhqrfjid() {
    return yhqrfjid;
  }

  public void setYhqrfjid(String yhqrfjid) {
    this.yhqrfjid = yhqrfjid;
  }

  public String getRwfjid() {
    return rwfjid;
  }

  public void setRwfjid(String rwfjid) {
    this.rwfjid = rwfjid;
  }
}
