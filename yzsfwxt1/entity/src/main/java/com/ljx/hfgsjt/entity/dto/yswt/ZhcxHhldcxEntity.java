package com.ljx.hfgsjt.entity.dto.yswt;

public class ZhcxHhldcxEntity {
  private Integer page = 1;// 分页：页数
  private Integer size = 20;// 分页：行数
  public int startRow = 0;// 分页:开始行数
  public int endRow = 0;// 分页：结束行数

  public String type;//类型

  private String kssj;//开始时间
  private String jssj;//结束时间
  private String d;
  private String xmbh;
  private String xmmc;
  private String xmfzr;
  private String activityname;
  private String rwblr;
  private String checkSx;
  private String checkTime;
  private String jssxYl;
  private String jssx;
  private String qszt;
  private String ld;
  private String hd;


  public int getPage() {
    return page;
  }

  public void setPage(Integer page) {
    this.page = page;
  }

  public Integer getSize() {
    return size;
  }

  public void setSize(Integer size) {
    this.size = size;
  }
  public int getStartRow() {
    return startRow;
  }

  public void setStartRow(int startRow) {
    this.startRow = startRow;
  }

  public void setStartRow() {
    this.startRow = (page - 1) * size + 1;
  }

  public int getEndRow() {
    return endRow;
  }

  public void setEndRow(int endRow) {
    this.endRow = endRow;
  }

  public void setEndRow() {
    this.endRow = page * size;
  }

  public String getXmbh() {
    return xmbh;
  }

  public void setXmbh(String xmbh) {
    this.xmbh = xmbh;
  }


  public String getXmmc() {
    return xmmc;
  }

  public void setXmmc(String xmmc) {
    this.xmmc = xmmc;
  }


  public String getXmfzr() {
    return xmfzr;
  }

  public void setXmfzr(String xmfzr) {
    this.xmfzr = xmfzr;
  }


  public String getActivityname() {
    return activityname;
  }

  public void setActivityname(String activityname) {
    this.activityname = activityname;
  }


  public String getRwblr() {
    return rwblr;
  }

  public void setRwblr(String rwblr) {
    this.rwblr = rwblr;
  }


  public String getCheckSx() {
    return checkSx;
  }

  public void setCheckSx(String checkSx) {
    this.checkSx = checkSx;
  }


  public String getCheckTime() {
    return checkTime;
  }

  public void setCheckTime(String checkTime) {
    this.checkTime = checkTime;
  }


  public String getJssxYl() {
    return jssxYl;
  }

  public void setJssxYl(String jssxYl) {
    this.jssxYl = jssxYl;
  }


  public String getJssj() {
    return jssj;
  }

  public void setJssj(String jssj) {
    this.jssj = jssj;
  }


  public String getJssx() {
    return jssx;
  }

  public void setJssx(String jssx) {
    this.jssx = jssx;
  }


  public String getQszt() {
    return qszt;
  }

  public void setQszt(String qszt) {
    this.qszt = qszt;
  }


  public String getLd() {
    return ld;
  }

  public void setLd(String ld) {
    this.ld = ld;
  }


  public String getHd() {
    return hd;
  }

  public void setHd(String hd) {
    this.hd = hd;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getD() {
    return d;
  }

  public void setD(String d) {
    this.d = d;
  }

  public String getKssj() {
    return kssj;
  }

  public void setKssj(String kssj) {
    this.kssj = kssj;
  }
}
