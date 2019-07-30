package com.ljx.hfgsjt.entity.yj;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

@Entity
@Table(name = "XM_FM")
public class XmFm {
  @Id
  private String id;
  private String fmbh;
  private String xmid;
  private String fmyxbh;
  private String azwz;
  private java.util.Date azrq;
  @Transient
  private String azrq_vue;
  private String fmkjbh;
  private String fmlxbh;
  private String fmcj;
  private String fmxh;
  private String fgfs;
  private String fgsd;
  private String fgczbh;
  private String zdfsbh;
  private String zdfxbh;
  private String qbzs;
  private String fmzybh;
  private String fjgczbh;
  private String fjgxsbh;
  private String gps;
  private java.util.Date czsj;
  @Transient
  private String czsj_vue;
  private String czr;
  private String bz;
  private String zt;
  private String sbid;
  @Transient
  private String fmkj;
  @Transient
  private String fmlx;
  @Transient
  private String fmzy;
  @Transient
  private String zdfs;
  @Transient
  private String zdfx;
  @Transient
  private String jgcz;
  @Transient
  private String jgxs;
  @Transient
  private String xmbh;
  @Transient
  private String czrname;
  @Transient
  private String fgcz;
  @Transient
  private String sbbzh;
  @Transient
  private boolean yzdzj;//已制点志记

  public boolean isYzdzj() {
    return yzdzj;
  }

  public void setYzdzj(boolean yzdzj) {
    this.yzdzj = yzdzj;
  }

  public String getSbbzh() {
    return sbbzh;
  }

  public void setSbbzh(String sbbzh) {
    this.sbbzh = sbbzh;
  }

  public String getFgcz() {
    return fgcz;
  }

  public void setFgcz(String fgcz) {
    this.fgcz = fgcz;
  }

  public String getCzrname() {
    return czrname;
  }

  public void setCzrname(String czrname) {
    this.czrname = czrname;
  }

  public String getXmbh() {
    return xmbh;
  }

  public void setXmbh(String xmbh) {
    this.xmbh = xmbh;
  }

  public String getCzsj_vue() {
    return czsj_vue;
  }

  public void setCzsj_vue(String czsj_vue) {
    this.czsj_vue = czsj_vue;
  }

  public String getFmkj() {
    return fmkj;
  }

  public void setFmkj(String fmkj) {
    this.fmkj = fmkj;
  }

  public String getFmlx() {
    return fmlx;
  }

  public void setFmlx(String fmlx) {
    this.fmlx = fmlx;
  }

  public String getFmzy() {
    return fmzy;
  }

  public void setFmzy(String fmzy) {
    this.fmzy = fmzy;
  }

  public String getZdfs() {
    return zdfs;
  }

  public void setZdfs(String zdfs) {
    this.zdfs = zdfs;
  }

  public String getZdfx() {
    return zdfx;
  }

  public void setZdfx(String zdfx) {
    this.zdfx = zdfx;
  }

  public String getJgcz() {
    return jgcz;
  }

  public void setJgcz(String jgcz) {
    this.jgcz = jgcz;
  }

  public String getJgxs() {
    return jgxs;
  }

  public void setJgxs(String jgxs) {
    this.jgxs = jgxs;
  }

  public String getAzrq_vue() {
    return azrq_vue;
  }

  public void setAzrq_vue(String azrq_vue) {
    this.azrq_vue = azrq_vue;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public String getFmbh() {
    return fmbh;
  }

  public void setFmbh(String fmbh) {
    this.fmbh = fmbh;
  }


  public String getXmid() {
    return xmid;
  }

  public void setXmid(String xmid) {
    this.xmid = xmid;
  }


  public String getFmyxbh() {
    return fmyxbh;
  }

  public void setFmyxbh(String fmyxbh) {
    this.fmyxbh = fmyxbh;
  }


  public String getAzwz() {
    return azwz;
  }

  public void setAzwz(String azwz) {
    this.azwz = azwz;
  }





  public String getFmkjbh() {
    return fmkjbh;
  }

  public void setFmkjbh(String fmkjbh) {
    this.fmkjbh = fmkjbh;
  }


  public String getFmlxbh() {
    return fmlxbh;
  }

  public void setFmlxbh(String fmlxbh) {
    this.fmlxbh = fmlxbh;
  }


  public String getFmcj() {
    return fmcj;
  }

  public void setFmcj(String fmcj) {
    this.fmcj = fmcj;
  }


  public String getFmxh() {
    return fmxh;
  }

  public void setFmxh(String fmxh) {
    this.fmxh = fmxh;
  }


  public String getFgfs() {
    return fgfs;
  }

  public void setFgfs(String fgfs) {
    this.fgfs = fgfs;
  }


  public String getFgsd() {
    return fgsd;
  }

  public void setFgsd(String fgsd) {
    this.fgsd = fgsd;
  }


  public String getFgczbh() {
    return fgczbh;
  }

  public void setFgczbh(String fgczbh) {
    this.fgczbh = fgczbh;
  }


  public String getZdfsbh() {
    return zdfsbh;
  }

  public void setZdfsbh(String zdfsbh) {
    this.zdfsbh = zdfsbh;
  }


  public String getZdfxbh() {
    return zdfxbh;
  }

  public void setZdfxbh(String zdfxbh) {
    this.zdfxbh = zdfxbh;
  }


  public String getQbzs() {
    return qbzs;
  }

  public void setQbzs(String qbzs) {
    this.qbzs = qbzs;
  }


  public String getFmzybh() {
    return fmzybh;
  }

  public void setFmzybh(String fmzybh) {
    this.fmzybh = fmzybh;
  }


  public String getFjgczbh() {
    return fjgczbh;
  }

  public void setFjgczbh(String fjgczbh) {
    this.fjgczbh = fjgczbh;
  }


  public String getFjgxsbh() {
    return fjgxsbh;
  }

  public void setFjgxsbh(String fjgxsbh) {
    this.fjgxsbh = fjgxsbh;
  }


  public String getGps() {
    return gps;
  }

  public void setGps(String gps) {
    this.gps = gps;
  }


  public Date getAzrq() {
    return azrq;
  }

  public void setAzrq(Date azrq) {
    this.azrq = azrq;
  }

  public Date getCzsj() {
    return czsj;
  }

  public void setCzsj(Date czsj) {
    this.czsj = czsj;
  }

  public String getCzr() {
    return czr;
  }

  public void setCzr(String czr) {
    this.czr = czr;
  }


  public String getBz() {
    return bz;
  }

  public void setBz(String bz) {
    this.bz = bz;
  }


  public String getZt() {
    return zt;
  }

  public void setZt(String zt) {
    this.zt = zt;
  }


  public String getSbid() {
    return sbid;
  }

  public void setSbid(String sbid) {
    this.sbid = sbid;
  }

}
