package com.ljx.hfgsjt.entity.dto.xkybz;

import com.ljx.hfgsjt.entity.dto.BaseEntity;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "GET_XCKC_FQ")
public class KcrwfqView extends BaseEntity {//查询没有发起过联合勘查的 已立项/未立项的项目

  private String lxzt;
  private String xmbh;
  private String sqbh;
  private String type;
  @Id
  private String id;
  private String lxr;
  private String lxrsjh;
  private String xmjl;
  private String xmmc;
  private String dwmc;
  private String xmdzSheng;
  private String xmdzShi;
  private String xmdzXzqybh;
  private String xmdz;
  private String slr;
  private String slrq;
  private String xzqybh;

  @Transient
  private String xmjd;//项目进度
  @Transient
  private String kssj;//开始时间
  @Transient
  private String jssj;//结束时间
  @Transient
  private String lxsj;//立项时间
  @Transient
  private String rwzt;//立项时间


  public String getLxzt() {
    return lxzt;
  }

  public void setLxzt(String lxzt) {
    this.lxzt = lxzt;
  }

  public String getXmbh() {
    return xmbh;
  }

  public void setXmbh(String xmbh) {
    this.xmbh = xmbh;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
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

  public String getXmjl() {
    return xmjl;
  }

  public void setXmjl(String xmjl) {
    this.xmjl = xmjl;
  }

  public String getXmmc() {
    return xmmc;
  }

  public void setXmmc(String xmmc) {
    this.xmmc = xmmc;
  }

  public String getDwmc() {
    return dwmc;
  }

  public void setDwmc(String dwmc) {
    this.dwmc = dwmc;
  }

  public String getXmdzSheng() {
    return xmdzSheng;
  }

  public void setXmdzSheng(String xmdzSheng) {
    this.xmdzSheng = xmdzSheng;
  }

  public String getXmdzShi() {
    return xmdzShi;
  }

  public void setXmdzShi(String xmdzShi) {
    this.xmdzShi = xmdzShi;
  }


  public String getXmdzXzqybh() {
    return xmdzXzqybh;
  }

  public void setXmdzXzqybh(String xmdzXzqybh) {
    this.xmdzXzqybh = xmdzXzqybh;
  }

  public String getXmdz() {
    return xmdz;
  }

  public void setXmdz(String xmdz) {
    this.xmdz = xmdz;
  }

  public String getSlr() {
    return slr;
  }

  public void setSlr(String slr) {
    this.slr = slr;
  }


  public String getSlrq() {
    return slrq;
  }

  public void setSlrq(String slrq) {
    this.slrq = slrq;
  }


  public String getXzqybh() {
    return xzqybh;
  }

  public void setXzqybh(String xzqybh) {
    this.xzqybh = xzqybh;
  }

  public String getXmjd() {
    return xmjd;
  }

  public void setXmjd(String xmjd) {
    this.xmjd = xmjd;
  }

  public String getKssj() {
    return kssj;
  }

  public void setKssj(String kssj) {
    this.kssj = kssj;
  }

  public String getJssj() {
    return jssj;
  }

  public void setJssj(String jssj) {
    this.jssj = jssj;
  }

  public String getLxsj() {
    return lxsj;
  }

  public void setLxsj(String lxsj) {
    this.lxsj = lxsj;
  }

  public String getSqbh() {
    return sqbh;
  }

  public void setSqbh(String sqbh) {
    this.sqbh = sqbh;
  }

  public String getRwzt() {
    return rwzt;
  }

  public void setRwzt(String rwzt) {
    this.rwzt = rwzt;
  }

  @Override
  public String toString() {
    SimpleDateFormat sdft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    return "{" +
            "lxzt:'" + lxzt + '\'' +
            ", xmbh:'" + xmbh + '\'' +
            ", sqbh:'" + sqbh + '\'' +
            ", type:'" + type + '\'' +
            ", id:'" + id + '\'' +
            ", lxr:'" + lxr + '\'' +
            ", lxrsjh:'" + lxrsjh + '\'' +
            ", xmjl:'" + xmjl + '\'' +
            ", xmmc:'" + xmmc + '\'' +
            ", dwmc:'" + dwmc + '\'' +
            ", xmdzSheng:'" + xmdzSheng + '\'' +
            ", xmdzShi:'" + xmdzShi + '\'' +
            ", xmdzXzqybh:'" + xmdzXzqybh + '\'' +
            ", xmdz:'" + xmdz + '\'' +
            ", slr:'" + slr + '\'' +
            ", slrq:'" + slrq  + '\''+
            ", xzqybh:'" + xzqybh + '\'' +
            ", xmjd:'" + xmjd + '\'' +
            ", kssj:'" + kssj + '\'' +
            ", jssj:'" + jssj + '\'' +
            ", lxsj:'" + lxsj + '\'' +
            '}';
  }
}
