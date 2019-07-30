package com.ljx.hfgsjt.entity.dto.js;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "YHJS_RWZB")
public class JsRwZb {

    @Id
    private String id;

    private String rwbh;

    private String xmid;

    private String fqr;

    private Date fqrq;

    private String fpr;

    private Date fpsj;

    private String ysry;

    private String cssftg;

    private String csry;

    private Date cssj;

    private String fssftg;

    private String fsry;

    private Date fssj;

    private String yhsfqr;

    private Date yhqrsj;

    private String zt;

    private String bz;

    private String shyj;

    public String getShyj() {
        return shyj;
    }

    public void setShyj(String shyj) {
        this.shyj = shyj;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRwbh() {
        return rwbh;
    }

    public void setRwbh(String rwbh) {
        this.rwbh = rwbh;
    }

    public String getXmid() {
        return xmid;
    }

    public void setXmid(String xmid) {
        this.xmid = xmid;
    }

    public String getFqr() {
        return fqr;
    }

    public void setFqr(String fqr) {
        this.fqr = fqr;
    }

    public Date getFqrq() {
        return fqrq;
    }

    public void setFqrq(Date fqrq) {
        this.fqrq = fqrq;
    }

    public String getFpr() {
        return fpr;
    }

    public void setFpr(String fpr) {
        this.fpr = fpr;
    }

    public Date getFpsj() {
        return fpsj;
    }

    public void setFpsj(Date fpsj) {
        this.fpsj = fpsj;
    }

    public String getYsry() {
        return ysry;
    }

    public void setYsry(String ysry) {
        this.ysry = ysry;
    }

    public String getCssftg() {
        return cssftg;
    }

    public void setCssftg(String cssftg) {
        this.cssftg = cssftg;
    }

    public String getCsry() {
        return csry;
    }

    public void setCsry(String csry) {
        this.csry = csry;
    }

    public Date getCssj() {
        return cssj;
    }

    public void setCssj(Date cssj) {
        this.cssj = cssj;
    }

    public String getFssftg() {
        return fssftg;
    }

    public void setFssftg(String fssftg) {
        this.fssftg = fssftg;
    }

    public String getFsry() {
        return fsry;
    }

    public void setFsry(String fsry) {
        this.fsry = fsry;
    }

    public Date getFssj() {
        return fssj;
    }

    public void setFssj(Date fssj) {
        this.fssj = fssj;
    }

    public String getYhsfqr() {
        return yhsfqr;
    }

    public void setYhsfqr(String yhsfqr) {
        this.yhsfqr = yhsfqr;
    }

    public Date getYhqrsj() {
        return yhqrsj;
    }

    public void setYhqrsj(Date yhqrsj) {
        this.yhqrsj = yhqrsj;
    }

    public String getZt() {
        return zt;
    }

    public void setZt(String zt) {
        this.zt = zt;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }
}
