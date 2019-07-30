package com.ljx.hfgsjt.entity.dto.Sjst;

import javax.persistence.*;

@Entity
@Table(name = "XM_SB")
public class XmSb {
@Id
  private String id;
  private String xmId;
  private String gcid;
  private String ldid;
  private String jffsbh;
  private String hhlxbh;
  private String ysxzbh;
  private String kjbh;
  private String zjlxbh;
  private String ykjbh;
  private String sqid;
  private String xh;
  private String hh;
  private String hm;
  private String dz;
  private String mph;
  private int type;
  private String kj;
  private String ds;
  private String ysxz;
  private String khyh;
  private String khmc;
  private String yhzh;
  private String yhh;
  private String yhm;
  private String ydz;
  private String ybkj;
  private String ybds;
  private String ysbcj;
  private String ysqcm;
  private Integer bzlb;
  private String sfzh;
  private String lxr;
  private String lxdh;
  private String sbcj;
  private String yqwcrq;
  private String sjwcrq;
  private String bch;
  private String gznr;
  private String gzyy;
  private String sgdw;
  private String czry;
  private String bz;
  private String bk;
  private String wtdw;
  private java.sql.Date slrq;
  private String zdr;
  private String azwz;
  private String azfs;
  private String sbje;
  private String gwjsfysje;
  private String gcfysje;
  private String sfxd;
  private java.sql.Date xdrq;
  private java.sql.Date sgsj;
  private String hbyhlxbh;
  private String zsljf;
  private String bsm;
  private java.sql.Date tssj;
  private java.sql.Date yssj;
  private String gwjsfssje;
  private String gwjsfjmje;
  private String gwjsfzt;
  private String gcfzt;
  private String hyflbh;
  private String pqbh;
  private String sbsgbh;
  private String sfwg;
  private String yt;
  private String jbfs;
  private String azwzxzq;
  private String sgry;
  private java.sql.Date sgryqzsj;
  private String yhqr;
  private java.sql.Date yhqrqzsj;
  private java.sql.Date yyzx_xcyjrq;
  private String yyzxSbds;
  private String yyzxSbkj;
  private String yyzxBsm;
  private String yyzxYsxzbh;
  private String yyzxHyflbh;
  private String yyzxSgry;
  private String yyzxGssry;
  private String yyzxYyzxry;
  private String yyzxCbsflsfw;
  private String yyzxBz;
  private String xfys;
  private String sfyj;
  private String sbcjbh;
  private String sgdw_sbkj;
  private String sfxdyj;
  private java.sql.Date xdyjrq;
  private String dxfw;
  private String dxmc;
  private String dxhm;
  private String wfinidHbsq;
  private String dbpzbh;
  private String dbpzcbid;
  private String zt;
  private String sblxbh;
  private String fq;
  private String yyzx_hh;
  private String gslxbh;
  private String gps;
  private String sbsxyz;
  private String xhsyz;
  private String cnxh;
  private String fqbh;
  private java.sql.Date zbwgsj;
  private java.sql.Date yjwcsj;
  private String bmbmZb;
  private String bmbmQs;
  @Transient
  private String yhbh;//银行编号
  @Transient
  private String ysxzname;//用水性质名称
  @Transient
  private String hyflname;//行业分类名称
  @Transient
  private String jffsname;//交费方式名称
  @Transient
  private String kjname;//口径名称
  @Transient
  private String ykjname;//原口径名称
  @Transient
  private String khyhname;//开户银行名称
  @Transient
  private String khzhname;//开户总行名称
  @Transient
  private String ldbh;//楼栋编号
  @Transient
  private String vjffs;//缴费方式
  @Transient
  private String vysxz;//用水性质
  @Transient
  private String vkjmc;//口径名称
  @Transient
  private String vsgkjmc;//施工单位口径
  @Transient
  private String vsblx;//水表类型

  @Transient
  private String vsbcj;//水表厂家

  @Transient
  private String ldmc;//楼栋名称

  @Transient
  private String yjzt;//移交状态

  @Transient
  private String sgdwSbkjName;//装表口径

  @Transient
  private String yshyfl;//验收行业分类
  @Transient
  private String mc;//表内修改标识

  //缴费
  @Transient
  private String jfzt;//缴费状态
  @Transient
  private String jfztname;//缴费状态
  @Transient
  private String gxzt;//勾选状态
  @Transient
  private String jfzbid;//缴费主表ID
  //出户改造
  private String counts;//数量
  @Transient
  private String sbyjdbh;//水表移交单编号
  @Transient
  private String sqlx;
  @Transient
  private boolean checkable;

  @Transient
  private String sgxdrwid;

  @Transient
  private String sftzdzbid;

  public String getSftzdzbid() {
    return sftzdzbid;
  }

  public void setSftzdzbid(String sftzdzbid) {
    this.sftzdzbid = sftzdzbid;
  }

  public boolean isCheckable() {
    return checkable;
  }

  public void setCheckable(boolean checkable) {
    this.checkable = checkable;
  }

  public String getSgxdrwid() {
    return sgxdrwid;
  }

  public void setSgxdrwid(String sgxtrwid) {
    this.sgxdrwid = sgxtrwid;
  }

  public String getSqlx() {
    return sqlx;
  }

  public void setSqlx(String sqlx) {
    this.sqlx = sqlx;
  }

  public String getYkjname() {
    return ykjname;
  }

  public void setYkjname(String ykjname) {
    this.ykjname = ykjname;
  }

  public String getSbyjdbh() {
    return sbyjdbh;
  }

  public void setSbyjdbh(String sbyjdbh) {
    this.sbyjdbh = sbyjdbh;
  }

  public String getCounts() {
    return counts;
  }

  public void setCounts(String counts) {
    this.counts = counts;
  }

  public String getJfzbid() {
    return jfzbid;
  }

  public void setJfzbid(String jfzbid) {
    this.jfzbid = jfzbid;
  }

  public String getGxzt() {
    return gxzt;
  }

  public void setGxzt(String gxzt) {
    this.gxzt = gxzt;
  }

  public String getJfztname() {
    return jfztname;
  }

  public void setJfztname(String jfztname) {
    this.jfztname = jfztname;
  }

  public String getJfzt() {
    return jfzt;
  }

  public void setJfzt(String jfzt) {
    this.jfzt = jfzt;
  }

  public String getMc() {
    return mc;
  }

  public void setMc(String mc) {
    this.mc = mc;
  }

  public String getYshyfl() {
    return yshyfl;
  }

  public void setYshyfl(String yshyfl) {
    this.yshyfl = yshyfl;
  }

  public String getSgdwSbkjName() {
    return sgdwSbkjName;
  }

  public void setSgdwSbkjName(String sgdwSbkjName) {
    this.sgdwSbkjName = sgdwSbkjName;
  }

  public String getVsbcj() {
    return vsbcj;
  }

  public void setVsbcj(String vsbcj) {
    this.vsbcj = vsbcj;
  }

  public String getYjzt() {
    return yjzt;
  }

  public void setYjzt(String yjzt) {
    this.yjzt = yjzt;
  }

  public String getLdmc() {
    return ldmc;
  }

  public void setLdmc(String ldmc) {
    this.ldmc = ldmc;
  }

  public String getVsblx() {
    return vsblx;
  }

  public void setVsblx(String vsblx) {
    this.vsblx = vsblx;
  }

  public String getVjffs() {
    return vjffs;
  }

  public String getVysxz() {
    return vysxz;
  }

  public String getVkjmc() {
    return vkjmc;
  }

  public String getVsgkjmc() {
    return vsgkjmc;
  }

  public void setVjffs(String vjffs) {
    this.vjffs = vjffs;
  }

  public void setVysxz(String vysxz) {
    this.vysxz = vysxz;
  }

  public void setVkjmc(String vkjmc) {
    this.vkjmc = vkjmc;
  }

  public void setVsgkjmc(String vsgkjmc) {
    this.vsgkjmc = vsgkjmc;
  }

  public String getLdbh() {
    return ldbh;
  }

  public void setLdbh(String ldbh) {
    this.ldbh = ldbh;
  }

  public String getYhbh() {
    return yhbh;
  }

  public void setYhbh(String yhbh) {
    this.yhbh = yhbh;
  }

  public String getYsxzname() {
    return ysxzname;
  }

  public void setYsxzname(String ysxzname) {
    this.ysxzname = ysxzname;
  }

  public String getHyflname() {
    return hyflname;
  }

  public void setHyflname(String hyflname) {
    this.hyflname = hyflname;
  }

  public String getJffsname() {
    return jffsname;
  }

  public void setJffsname(String jffsname) {
    this.jffsname = jffsname;
  }

  public String getKjname() {
    return kjname;
  }

  public void setKjname(String kjname) {
    this.kjname = kjname;
  }

  public String getKhyhname() {
    return khyhname;
  }

  public void setKhyhname(String khyhname) {
    this.khyhname = khyhname;
  }

  public String getKhzhname() {
    return khzhname;
  }

  public void setKhzhname(String khzhname) {
    this.khzhname = khzhname;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public String getXmId() {
    return xmId;
  }

  public void setXmId(String xmId) {
    this.xmId = xmId;
  }


  public String getGcid() {
    return gcid;
  }

  public void setGcid(String gcid) {
    this.gcid = gcid;
  }


  public String getLdid() {
    return ldid;
  }

  public void setLdid(String ldid) {
    this.ldid = ldid;
  }


  public String getJffsbh() {
    return jffsbh;
  }

  public void setJffsbh(String jffsbh) {
    this.jffsbh = jffsbh;
  }


  public String getHhlxbh() {
    return hhlxbh;
  }

  public void setHhlxbh(String hhlxbh) {
    this.hhlxbh = hhlxbh;
  }


  public String getYsxzbh() {
    return ysxzbh;
  }

  public void setYsxzbh(String ysxzbh) {
    this.ysxzbh = ysxzbh;
  }


  public String getKjbh() {
    return kjbh;
  }

  public void setKjbh(String kjbh) {
    this.kjbh = kjbh;
  }


  public String getZjlxbh() {
    return zjlxbh;
  }

  public void setZjlxbh(String zjlxbh) {
    this.zjlxbh = zjlxbh;
  }


  public String getYkjbh() {
    return ykjbh;
  }

  public void setYkjbh(String ykjbh) {
    this.ykjbh = ykjbh;
  }


  public String getSqid() {
    return sqid;
  }

  public void setSqid(String sqid) {
    this.sqid = sqid;
  }


  public String getXh() {
    return xh;
  }

  public void setXh(String xh) {
    this.xh = xh;
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


  public String getMph() {
    return mph;
  }

  public void setMph(String mph) {
    this.mph = mph;
  }


  public int getType() {
    return type;
  }

  public void setType(int type) {
    this.type = type;
  }


  public String getKj() {
    return kj;
  }

  public void setKj(String kj) {
    this.kj = kj;
  }


  public String getDs() {
    return ds;
  }

  public void setDs(String ds) {
    this.ds = ds;
  }


  public String getYsxz() {
    return ysxz;
  }

  public void setYsxz(String ysxz) {
    this.ysxz = ysxz;
  }


  public String getKhyh() {
    return khyh;
  }

  public void setKhyh(String khyh) {
    this.khyh = khyh;
  }


  public String getKhmc() {
    return khmc;
  }

  public void setKhmc(String khmc) {
    this.khmc = khmc;
  }


  public String getYhzh() {
    return yhzh;
  }

  public void setYhzh(String yhzh) {
    this.yhzh = yhzh;
  }


  public String getYhh() {
    return yhh;
  }

  public void setYhh(String yhh) {
    this.yhh = yhh;
  }


  public String getYhm() {
    return yhm;
  }

  public void setYhm(String yhm) {
    this.yhm = yhm;
  }


  public String getYdz() {
    return ydz;
  }

  public void setYdz(String ydz) {
    this.ydz = ydz;
  }


  public String getYbkj() {
    return ybkj;
  }

  public void setYbkj(String ybkj) {
    this.ybkj = ybkj;
  }


  public String getYbds() {
    return ybds;
  }

  public void setYbds(String ybds) {
    this.ybds = ybds;
  }


  public String getYsbcj() {
    return ysbcj;
  }

  public void setYsbcj(String ysbcj) {
    this.ysbcj = ysbcj;
  }


  public String getYsqcm() {
    return ysqcm;
  }

  public void setYsqcm(String ysqcm) {
    this.ysqcm = ysqcm;
  }


  public Integer getBzlb() {
    return bzlb;
  }

  public void setBzlb(Integer bzlb) {
    this.bzlb = bzlb;
  }


  public String getSfzh() {
    return sfzh;
  }

  public void setSfzh(String sfzh) {
    this.sfzh = sfzh;
  }


  public String getLxr() {
    return lxr;
  }

  public void setLxr(String lxr) {
    this.lxr = lxr;
  }


  public String getLxdh() {
    return lxdh;
  }

  public void setLxdh(String lxdh) {
    this.lxdh = lxdh;
  }


  public String getSbcj() {
    return sbcj;
  }

  public void setSbcj(String sbcj) {
    this.sbcj = sbcj;
  }


  public String getYqwcrq() {
    return yqwcrq;
  }

  public void setYqwcrq(String yqwcrq) {
    this.yqwcrq = yqwcrq;
  }


  public String getSjwcrq() {
    return sjwcrq;
  }

  public void setSjwcrq(String sjwcrq) {
    this.sjwcrq = sjwcrq;
  }


  public String getBch() {
    return bch;
  }

  public void setBch(String bch) {
    this.bch = bch;
  }


  public String getGznr() {
    return gznr;
  }

  public void setGznr(String gznr) {
    this.gznr = gznr;
  }


  public String getGzyy() {
    return gzyy;
  }

  public void setGzyy(String gzyy) {
    this.gzyy = gzyy;
  }


  public String getSgdw() {
    return sgdw;
  }

  public void setSgdw(String sgdw) {
    this.sgdw = sgdw;
  }


  public String getCzry() {
    return czry;
  }

  public void setCzry(String czry) {
    this.czry = czry;
  }


  public String getBz() {
    return bz;
  }

  public void setBz(String bz) {
    this.bz = bz;
  }


  public String getBk() {
    return bk;
  }

  public void setBk(String bk) {
    this.bk = bk;
  }


  public String getWtdw() {
    return wtdw;
  }

  public void setWtdw(String wtdw) {
    this.wtdw = wtdw;
  }


  public java.sql.Date getSlrq() {
    return slrq;
  }

  public void setSlrq(java.sql.Date slrq) {
    this.slrq = slrq;
  }


  public String getZdr() {
    return zdr;
  }

  public void setZdr(String zdr) {
    this.zdr = zdr;
  }


  public String getAzwz() {
    return azwz;
  }

  public void setAzwz(String azwz) {
    this.azwz = azwz;
  }


  public String getAzfs() {
    return azfs;
  }

  public void setAzfs(String azfs) {
    this.azfs = azfs;
  }


  public String getSbje() {
    return sbje;
  }

  public void setSbje(String sbje) {
    this.sbje = sbje;
  }


  public String getGwjsfysje() {
    return gwjsfysje;
  }

  public void setGwjsfysje(String gwjsfysje) {
    this.gwjsfysje = gwjsfysje;
  }


  public String getGcfysje() {
    return gcfysje;
  }

  public void setGcfysje(String gcfysje) {
    this.gcfysje = gcfysje;
  }


  public String getSfxd() {
    return sfxd;
  }

  public void setSfxd(String sfxd) {
    this.sfxd = sfxd;
  }


  public java.sql.Date getXdrq() {
    return xdrq;
  }

  public void setXdrq(java.sql.Date xdrq) {
    this.xdrq = xdrq;
  }


  public java.sql.Date getSgsj() {
    return sgsj;
  }

  public void setSgsj(java.sql.Date sgsj) {
    this.sgsj = sgsj;
  }


  public String getHbyhlxbh() {
    return hbyhlxbh;
  }

  public void setHbyhlxbh(String hbyhlxbh) {
    this.hbyhlxbh = hbyhlxbh;
  }


  public String getZsljf() {
    return zsljf;
  }

  public void setZsljf(String zsljf) {
    this.zsljf = zsljf;
  }


  public String getBsm() {
    return bsm;
  }

  public void setBsm(String bsm) {
    this.bsm = bsm;
  }


  public java.sql.Date getTssj() {
    return tssj;
  }

  public void setTssj(java.sql.Date tssj) {
    this.tssj = tssj;
  }


  public java.sql.Date getYssj() {
    return yssj;
  }

  public void setYssj(java.sql.Date yssj) {
    this.yssj = yssj;
  }


  public String getGwjsfssje() {
    return gwjsfssje;
  }

  public void setGwjsfssje(String gwjsfssje) {
    this.gwjsfssje = gwjsfssje;
  }


  public String getGwjsfjmje() {
    return gwjsfjmje;
  }

  public void setGwjsfjmje(String gwjsfjmje) {
    this.gwjsfjmje = gwjsfjmje;
  }


  public String getGwjsfzt() {
    return gwjsfzt;
  }

  public void setGwjsfzt(String gwjsfzt) {
    this.gwjsfzt = gwjsfzt;
  }


  public String getGcfzt() {
    return gcfzt;
  }

  public void setGcfzt(String gcfzt) {
    this.gcfzt = gcfzt;
  }


  public String getHyflbh() {
    return hyflbh;
  }

  public void setHyflbh(String hyflbh) {
    this.hyflbh = hyflbh;
  }


  public String getPqbh() {
    return pqbh;
  }

  public void setPqbh(String pqbh) {
    this.pqbh = pqbh;
  }


  public String getSbsgbh() {
    return sbsgbh;
  }

  public void setSbsgbh(String sbsgbh) {
    this.sbsgbh = sbsgbh;
  }


  public String getSfwg() {
    return sfwg;
  }

  public void setSfwg(String sfwg) {
    this.sfwg = sfwg;
  }


  public String getYt() {
    return yt;
  }

  public void setYt(String yt) {
    this.yt = yt;
  }


  public String getJbfs() {
    return jbfs;
  }

  public void setJbfs(String jbfs) {
    this.jbfs = jbfs;
  }


  public String getAzwzxzq() {
    return azwzxzq;
  }

  public void setAzwzxzq(String azwzxzq) {
    this.azwzxzq = azwzxzq;
  }


  public String getSgry() {
    return sgry;
  }

  public void setSgry(String sgry) {
    this.sgry = sgry;
  }


  public java.sql.Date getSgryqzsj() {
    return sgryqzsj;
  }

  public void setSgryqzsj(java.sql.Date sgryqzsj) {
    this.sgryqzsj = sgryqzsj;
  }


  public String getYhqr() {
    return yhqr;
  }

  public void setYhqr(String yhqr) {
    this.yhqr = yhqr;
  }


  public java.sql.Date getYhqrqzsj() {
    return yhqrqzsj;
  }

  public void setYhqrqzsj(java.sql.Date yhqrqzsj) {
    this.yhqrqzsj = yhqrqzsj;
  }

  @Basic
  @Column(name = "YYZX_XCYJRQ")
  public java.sql.Date getYyzx_xcyjrq() {
    return yyzx_xcyjrq;
  }

  public void setYyzx_xcyjrq(java.sql.Date yyzx_xcyjrq) {
    this.yyzx_xcyjrq = yyzx_xcyjrq;
  }

  @Basic
  @Column(name = "YYZX_SBDS")
  public String getYyzxSbds() {
    return yyzxSbds;
  }

  public void setYyzxSbds(String yyzxSbds) {
    this.yyzxSbds = yyzxSbds;
  }

  @Basic
  @Column(name = "YYZX_SBKJ")
  public String getYyzxSbkj() {
    return yyzxSbkj;
  }

  public void setYyzxSbkj(String yyzxSbkj) {
    this.yyzxSbkj = yyzxSbkj;
  }

  @Basic
  @Column(name = "YYZX_BSM")
  public String getYyzxBsm() {
    return yyzxBsm;
  }

  public void setYyzxBsm(String yyzxBsm) {
    this.yyzxBsm = yyzxBsm;
  }


  public String getYyzxYsxzbh() {
    return yyzxYsxzbh;
  }

  public void setYyzxYsxzbh(String yyzxYsxzbh) {
    this.yyzxYsxzbh = yyzxYsxzbh;
  }


  public String getYyzxHyflbh() {
    return yyzxHyflbh;
  }

  public void setYyzxHyflbh(String yyzxHyflbh) {
    this.yyzxHyflbh = yyzxHyflbh;
  }


  public String getYyzxSgry() {
    return yyzxSgry;
  }

  public void setYyzxSgry(String yyzxSgry) {
    this.yyzxSgry = yyzxSgry;
  }


  public String getYyzxGssry() {
    return yyzxGssry;
  }

  public void setYyzxGssry(String yyzxGssry) {
    this.yyzxGssry = yyzxGssry;
  }


  public String getYyzxYyzxry() {
    return yyzxYyzxry;
  }

  public void setYyzxYyzxry(String yyzxYyzxry) {
    this.yyzxYyzxry = yyzxYyzxry;
  }


  public String getYyzxCbsflsfw() {
    return yyzxCbsflsfw;
  }

  public void setYyzxCbsflsfw(String yyzxCbsflsfw) {
    this.yyzxCbsflsfw = yyzxCbsflsfw;
  }


  public String getYyzxBz() {
    return yyzxBz;
  }

  public void setYyzxBz(String yyzxBz) {
    this.yyzxBz = yyzxBz;
  }


  public String getXfys() {
    return xfys;
  }

  public void setXfys(String xfys) {
    this.xfys = xfys;
  }


  public String getSfyj() {
    return sfyj;
  }

  public void setSfyj(String sfyj) {
    this.sfyj = sfyj;
  }


  public String getSbcjbh() {
    return sbcjbh;
  }

  public void setSbcjbh(String sbcjbh) {
    this.sbcjbh = sbcjbh;
  }

  @Basic
  @Column(name = "SGDW_SBKJ")
  public String getSgdw_sbkj() {
    return sgdw_sbkj;
  }

  public void setSgdw_sbkj(String sgdw_sbkj) {
    this.sgdw_sbkj = sgdw_sbkj;
  }


  public String getSfxdyj() {
    return sfxdyj;
  }

  public void setSfxdyj(String sfxdyj) {
    this.sfxdyj = sfxdyj;
  }


  public java.sql.Date getXdyjrq() {
    return xdyjrq;
  }

  public void setXdyjrq(java.sql.Date xdyjrq) {
    this.xdyjrq = xdyjrq;
  }


  public String getDxfw() {
    return dxfw;
  }

  public void setDxfw(String dxfw) {
    this.dxfw = dxfw;
  }


  public String getDxmc() {
    return dxmc;
  }

  public void setDxmc(String dxmc) {
    this.dxmc = dxmc;
  }


  public String getDxhm() {
    return dxhm;
  }

  public void setDxhm(String dxhm) {
    this.dxhm = dxhm;
  }


  public String getWfinidHbsq() {
    return wfinidHbsq;
  }

  public void setWfinidHbsq(String wfinidHbsq) {
    this.wfinidHbsq = wfinidHbsq;
  }


  public String getDbpzbh() {
    return dbpzbh;
  }

  public void setDbpzbh(String dbpzbh) {
    this.dbpzbh = dbpzbh;
  }


  public String getDbpzcbid() {
    return dbpzcbid;
  }

  public void setDbpzcbid(String dbpzcbid) {
    this.dbpzcbid = dbpzcbid;
  }


  public String getZt() {
    return zt;
  }

  public void setZt(String zt) {
    this.zt = zt;
  }


  public String getSblxbh() {
    return sblxbh;
  }

  public void setSblxbh(String sblxbh) {
    this.sblxbh = sblxbh;
  }


  public String getFq() {
    return fq;
  }

  public void setFq(String fq) {
    this.fq = fq;
  }

  @Basic
  @Column(name = "YYZX_HH")
  public String getYyzx_hh() {
    return yyzx_hh;
  }

  public void setYyzx_hh(String yyzx_hh) {
    this.yyzx_hh = yyzx_hh;
  }


  public String getGslxbh() {
    return gslxbh;
  }

  public void setGslxbh(String gslxbh) {
    this.gslxbh = gslxbh;
  }


  public String getGps() {
    return gps;
  }

  public void setGps(String gps) {
    this.gps = gps;
  }


  public String getSbsxyz() {
    return sbsxyz;
  }

  public void setSbsxyz(String sbsxyz) {
    this.sbsxyz = sbsxyz;
  }


  public String getXhsyz() {
    return xhsyz;
  }

  public void setXhsyz(String xhsyz) {
    this.xhsyz = xhsyz;
  }


  public String getCnxh() {
    return cnxh;
  }

  public void setCnxh(String cnxh) {
    this.cnxh = cnxh;
  }


  public String getFqbh() {
    return fqbh;
  }

  public void setFqbh(String fqbh) {
    this.fqbh = fqbh;
  }


  public java.sql.Date getZbwgsj() {
    return zbwgsj;
  }

  public void setZbwgsj(java.sql.Date zbwgsj) {
    this.zbwgsj = zbwgsj;
  }


  public java.sql.Date getYjwcsj() {
    return yjwcsj;
  }

  public void setYjwcsj(java.sql.Date yjwcsj) {
    this.yjwcsj = yjwcsj;
  }


  public String getBmbmZb() {
    return bmbmZb;
  }

  public void setBmbmZb(String bmbmZb) {
    this.bmbmZb = bmbmZb;
  }


  public String getBmbmQs() {
    return bmbmQs;
  }

  public void setBmbmQs(String bmbmQs) {
    this.bmbmQs = bmbmQs;
  }

}
