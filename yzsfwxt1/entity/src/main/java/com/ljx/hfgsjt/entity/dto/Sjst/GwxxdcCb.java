package com.ljx.hfgsjt.entity.dto.Sjst;

import com.ljx.hfgsjt.entity.dto.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

@Entity
@Table(name="SJST_GWDCCB")
public class GwxxdcCb extends BaseEntity {
    @Id
    private String id;

    private String gwdczbid;//官网调查主表关联id

    private String dcnr;//调查内容

    private String gxzl;//管线主路

    private String gxld;//管线路段

    private String gxjzl;//管线距主路

    private String gxzzl;//管线在主路

    private String gxzsf;//管线正上方

    private String gxkj;//管线口径  XTGL_KJ

    private String gxcz;//管线材质 码表中获取

    private String cydx;//测压对象

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date cysj;//测压时间

    private String dcdwz;//调查点位置

    private String cydbg;//测压点标高

    private String cydyl;//测压点压力
    private String cydsd;//测压点深度

    private String gwyj;//管网移交

    private String bwts;//并网通水

    private String dcr;//调查人

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dcrq;//调查日期

    private String bz;//备注

    @Transient
    private String dcrq_str;

    @Transient
    private String cysj_str;
    @Transient
    private String gxcz_str;
    @Transient
    private String kj_str;
    @Transient
    private String zsffgw_str;
    @Transient
    private String zlwz_str;


    public String getGxcz_str() {
        return gxcz_str;
    }

    public void setGxcz_str(String gxcz_str) {
        this.gxcz_str = gxcz_str;
    }

    public String getKj_str() {
        return kj_str;
    }

    public void setKj_str(String kj_str) {
        this.kj_str = kj_str;
    }

    public String getZsffgw_str() {
        return zsffgw_str;
    }

    public void setZsffgw_str(String zsffgw_str) {
        this.zsffgw_str = zsffgw_str;
    }

    public String getZlwz_str() {
        return zlwz_str;
    }

    public void setZlwz_str(String zlwz_str) {
        this.zlwz_str = zlwz_str;
    }

    public String getDcnr() {
        return dcnr;
    }

    public void setDcnr(String dcnr) {
        this.dcnr = dcnr;
    }

    public String getDcrq_str() {
        return dcrq_str;
    }

    public void setDcrq_str(String dcrq_str) {
        this.dcrq_str = dcrq_str;
    }

    public String getCysj_str() {
        return cysj_str;
    }

    public void setCysj_str(String cysj_str) {
        this.cysj_str = cysj_str;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGwdczbid() {
        return gwdczbid;
    }

    public void setGwdczbid(String gwdczbid) {
        this.gwdczbid = gwdczbid;
    }

    public String getGxzl() {
        return gxzl;
    }

    public void setGxzl(String gxzl) {
        this.gxzl = gxzl;
    }

    public String getGxld() {
        return gxld;
    }

    public void setGxld(String gxld) {
        this.gxld = gxld;
    }

    public String getGxjzl() {
        return gxjzl;
    }

    public void setGxjzl(String gxjzl) {
        this.gxjzl = gxjzl;
    }

    public String getGxzzl() {
        return gxzzl;
    }

    public void setGxzzl(String gxzzl) {
        this.gxzzl = gxzzl;
    }

    public String getGxzsf() {
        return gxzsf;
    }

    public void setGxzsf(String gxzsf) {
        this.gxzsf = gxzsf;
    }

    public String getGxkj() {
        return gxkj;
    }

    public void setGxkj(String gxkj) {
        this.gxkj = gxkj;
    }

    public String getGxcz() {
        return gxcz;
    }

    public void setGxcz(String gxcz) {
        this.gxcz = gxcz;
    }

    public String getCydx() {
        return cydx;
    }

    public void setCydx(String cydx) {
        this.cydx = cydx;
    }

    public Date getCysj() {
        return cysj;
    }

    public void setCysj(Date cysj) {
        this.cysj = cysj;
    }

    public String getDcdwz() {
        return dcdwz;
    }

    public void setDcdwz(String dcdwz) {
        this.dcdwz = dcdwz;
    }

    public String getCydbg() {
        return cydbg;
    }

    public void setCydbg(String cydbg) {
        this.cydbg = cydbg;
    }

    public String getCydyl() {
        return cydyl;
    }

    public void setCydyl(String cydyl) {
        this.cydyl = cydyl;
    }

    public String getGwyj() {
        return gwyj;
    }

    public void setGwyj(String gwyj) {
        this.gwyj = gwyj;
    }

    public String getBwts() {
        return bwts;
    }

    public void setBwts(String bwts) {
        this.bwts = bwts;
    }

    public String getDcr() {
        return dcr;
    }

    public void setDcr(String dcr) {
        this.dcr = dcr;
    }

    public Date getDcrq() {
        return dcrq;
    }

    public void setDcrq(Date dcrq) {
        this.dcrq = dcrq;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getCydsd() {
        return cydsd;
    }

    public void setCydsd(String cydsd) {
        this.cydsd = cydsd;
    }
}