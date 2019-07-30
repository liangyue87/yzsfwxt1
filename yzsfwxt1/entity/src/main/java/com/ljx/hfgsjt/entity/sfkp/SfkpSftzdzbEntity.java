package com.ljx.hfgsjt.entity.sfkp;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "SFKP_SFTZDZB")
public class SfkpSftzdzbEntity {

  @Id
  private String id;
  private String tzdbh;
  private String xmid;
  private String ytzdbh;
  private String tzdzt;
  private String zhmc;
  private String zhyh;
  private String zh;
  private String ysje;
  private String jmje;
  private String ssje;
  private String zdr;
  private java.sql.Date zdsj;
  private String jfdw;
  private String pjlx;
  private String pjh;
  private String jfqrr;
  private java.sql.Date jfqrsj;
  private String bz;
  private String jmlx;
  private java.sql.Date skrq;
  private String skbz;
  private String jfje;
  private String xmqk;
  private String zbjcwfl;
  private String printed;
  private String jfqrrbmbh;
  private java.sql.Date qrjzrq;
  private String htid;
  private java.sql.Date kprq;

  @Transient
  private String username;
  @Transient
  private String lxfs;
  @Transient
  private String zdsj_str;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getZdsj_str() {
    return zdsj_str;
  }

  public void setZdsj_str(String zdsj_str) {
    this.zdsj_str = zdsj_str;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public String getTzdbh() {
    return tzdbh;
  }

  public void setTzdbh(String tzdbh) {
    this.tzdbh = tzdbh;
  }


  public String getXmid() {
    return xmid;
  }

  public void setXmid(String xmid) {
    this.xmid = xmid;
  }


  public String getYtzdbh() {
    return ytzdbh;
  }

  public void setYtzdbh(String ytzdbh) {
    this.ytzdbh = ytzdbh;
  }


  public String getTzdzt() {
    return tzdzt;
  }

  public void setTzdzt(String tzdzt) {
    this.tzdzt = tzdzt;
  }


  public String getZhmc() {
    return zhmc;
  }

  public void setZhmc(String zhmc) {
    this.zhmc = zhmc;
  }


  public String getZhyh() {
    return zhyh;
  }

  public void setZhyh(String zhyh) {
    this.zhyh = zhyh;
  }


  public String getZh() {
    return zh;
  }

  public void setZh(String zh) {
    this.zh = zh;
  }


  public String getYsje() {
    return ysje;
  }

  public void setYsje(String ysje) {
    this.ysje = ysje;
  }


  public String getJmje() {
    return jmje;
  }

  public void setJmje(String jmje) {
    this.jmje = jmje;
  }


  public String getSsje() {
    return ssje;
  }

  public void setSsje(String ssje) {
    this.ssje = ssje;
  }


  public String getZdr() {
    return zdr;
  }

  public void setZdr(String zdr) {
    this.zdr = zdr;
  }


  public java.sql.Date getZdsj() {
    return zdsj;
  }

  public void setZdsj(java.sql.Date zdsj) {
    this.zdsj = zdsj;
  }


  public String getJfdw() {
    return jfdw;
  }

  public void setJfdw(String jfdw) {
    this.jfdw = jfdw;
  }


  public String getPjlx() {
    return pjlx;
  }

  public void setPjlx(String pjlx) {
    this.pjlx = pjlx;
  }


  public String getPjh() {
    return pjh;
  }

  public void setPjh(String pjh) {
    this.pjh = pjh;
  }


  public String getJfqrr() {
    return jfqrr;
  }

  public void setJfqrr(String jfqrr) {
    this.jfqrr = jfqrr;
  }


  public java.sql.Date getJfqrsj() {
    return jfqrsj;
  }

  public void setJfqrsj(java.sql.Date jfqrsj) {
    this.jfqrsj = jfqrsj;
  }


  public String getBz() {
    return bz;
  }

  public void setBz(String bz) {
    this.bz = bz;
  }


  public String getJmlx() {
    return jmlx;
  }

  public void setJmlx(String jmlx) {
    this.jmlx = jmlx;
  }


  public java.sql.Date getSkrq() {
    return skrq;
  }

  public void setSkrq(java.sql.Date skrq) {
    this.skrq = skrq;
  }


  public String getSkbz() {
    return skbz;
  }

  public void setSkbz(String skbz) {
    this.skbz = skbz;
  }


  public String getJfje() {
    return jfje;
  }

  public void setJfje(String jfje) {
    this.jfje = jfje;
  }


  public String getXmqk() {
    return xmqk;
  }

  public void setXmqk(String xmqk) {
    this.xmqk = xmqk;
  }


  public String getZbjcwfl() {
    return zbjcwfl;
  }

  public void setZbjcwfl(String zbjcwfl) {
    this.zbjcwfl = zbjcwfl;
  }


  public String getPrinted() {
    return printed;
  }

  public void setPrinted(String printed) {
    this.printed = printed;
  }


  public String getJfqrrbmbh() {
    return jfqrrbmbh;
  }

  public void setJfqrrbmbh(String jfqrrbmbh) {
    this.jfqrrbmbh = jfqrrbmbh;
  }


  public java.sql.Date getQrjzrq() {
    return qrjzrq;
  }

  public void setQrjzrq(java.sql.Date qrjzrq) {
    this.qrjzrq = qrjzrq;
  }


  public String getHtid() {
    return htid;
  }

  public void setHtid(String htid) {
    this.htid = htid;
  }


  public java.sql.Date getKprq() {
    return kprq;
  }

  public void setKprq(java.sql.Date kprq) {
    this.kprq = kprq;
  }

  public String getLxfs() {
    return lxfs;
  }

  public void setLxfs(String lxfs) {
    this.lxfs = lxfs;
  }
}
