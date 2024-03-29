package com.ljx.hfgsjt.entity.dto.yswt;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "YS_VZP_NEW")
public class YsVzpNewEntity {

  private String yszt;//预算状态 0 待预算  1 已预算
  private String startuser;//开户人
  private java.sql.Date startdt;//开户时间
  private String pdefId;
  private String czr;
  private java.sql.Date qssj;
  private String ischeck;
  private java.sql.Date checkSx;
  private String qszt;
  private String qssfcs;
  private java.sql.Date checkTime;
  private java.sql.Date jssx;
  private String sfhuangd;
  private java.sql.Date jssxYl;
  private String sfhongd;
  private String id;
  @Id
  private String xmbh;//项目编号
  private String sqlx;
  private String azlx;//安装类型（编码）
  private String codename;//安装类型
  private String sqbh;
  private String xmmc;
  private String xmdz;
  private String lxr;//联系人
  private String lxrsjh;//联系人手机号
  private String dwmc;//单位名称
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private String lxsj;//立项时间
  private String procinstid;//当前实例的id


  public String getYszt() {
    return yszt;
  }

  public void setYszt(String yszt) {
    this.yszt = yszt;
  }


  public String getStartuser() {
    return startuser;
  }

  public void setStartuser(String startuser) {
    this.startuser = startuser;
  }


  public java.sql.Date getStartdt() {
    return startdt;
  }

  public void setStartdt(java.sql.Date startdt) {
    this.startdt = startdt;
  }


  public String getPdefId() {
    return pdefId;
  }

  public void setPdefId(String pdefId) {
    this.pdefId = pdefId;
  }


  public String getCzr() {
    return czr;
  }

  public void setCzr(String czr) {
    this.czr = czr;
  }


  public java.sql.Date getQssj() {
    return qssj;
  }

  public void setQssj(java.sql.Date qssj) {
    this.qssj = qssj;
  }


  public String getIscheck() {
    return ischeck;
  }

  public void setIscheck(String ischeck) {
    this.ischeck = ischeck;
  }


  public java.sql.Date getCheckSx() {
    return checkSx;
  }

  public void setCheckSx(java.sql.Date checkSx) {
    this.checkSx = checkSx;
  }


  public String getQszt() {
    return qszt;
  }

  public void setQszt(String qszt) {
    this.qszt = qszt;
  }


  public String getQssfcs() {
    return qssfcs;
  }

  public void setQssfcs(String qssfcs) {
    this.qssfcs = qssfcs;
  }


  public java.sql.Date getCheckTime() {
    return checkTime;
  }

  public void setCheckTime(java.sql.Date checkTime) {
    this.checkTime = checkTime;
  }


  public java.sql.Date getJssx() {
    return jssx;
  }

  public void setJssx(java.sql.Date jssx) {
    this.jssx = jssx;
  }


  public String getSfhuangd() {
    return sfhuangd;
  }

  public void setSfhuangd(String sfhuangd) {
    this.sfhuangd = sfhuangd;
  }


  public java.sql.Date getJssxYl() {
    return jssxYl;
  }

  public void setJssxYl(java.sql.Date jssxYl) {
    this.jssxYl = jssxYl;
  }


  public String getSfhongd() {
    return sfhongd;
  }

  public void setSfhongd(String sfhongd) {
    this.sfhongd = sfhongd;
  }


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public String getXmbh() {
    return xmbh;
  }

  public void setXmbh(String xmbh) {
    this.xmbh = xmbh;
  }


  public String getSqlx() {
    return sqlx;
  }

  public void setSqlx(String sqlx) {
    this.sqlx = sqlx;
  }


  public String getAzlx() {
    return azlx;
  }

  public void setAzlx(String azlx) {
    this.azlx = azlx;
  }


  public String getSqbh() {
    return sqbh;
  }

  public void setSqbh(String sqbh) {
    this.sqbh = sqbh;
  }


  public String getXmmc() {
    return xmmc;
  }

  public void setXmmc(String xmmc) {
    this.xmmc = xmmc;
  }


  public String getXmdz() {
    return xmdz;
  }

  public void setXmdz(String xmdz) {
    this.xmdz = xmdz;
  }


  public String getLxr() {
    return lxr;
  }

  public void setLxr(String lxr) {
    this.lxr = lxr;
  }


  public String getLxrsjh() {
    return lxrsjh;
  }

  public void setLxrsjh(String lxrsjh) {
    this.lxrsjh = lxrsjh;
  }


  public String getDwmc() {
    return dwmc;
  }

  public void setDwmc(String dwmc) {
    this.dwmc = dwmc;
  }


  public String getLxsj() {
    return lxsj;
  }

  public void setLxsj(String lxsj) {
    this.lxsj = lxsj;
  }

  public String getProcinstid() {
    return procinstid;
  }

  public void setProcinstid(String procinstid) {
    this.procinstid = procinstid;
  }

  public String getCodename() {
    return codename;
  }

  public void setCodename(String codename) {
    this.codename = codename;
  }
}
