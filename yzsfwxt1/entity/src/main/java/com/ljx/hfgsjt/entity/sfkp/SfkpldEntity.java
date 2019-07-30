package com.ljx.hfgsjt.entity.sfkp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "GET_JFLD")
public class SfkpldEntity {
    private String tymc;
    private String ldid;
    private String xmid;
    private String ldbh;
    private String lfsx;
    private String zhs;
    private String yjsl;
    private String wyjsl;
    private String gxsl;

    public String getTymc() {
        return tymc;
    }

    public void setTymc(String tymc) {
        this.tymc = tymc;
    }

    @Id
    public String getLdid() {
        return ldid;
    }

    public void setLdid(String ldid) {
        this.ldid = ldid;
    }

    public String getXmid() {
        return xmid;
    }

    public void setXmid(String xmid) {
        this.xmid = xmid;
    }

    public String getLdbh() {
        return ldbh;
    }

    public void setLdbh(String ldbh) {
        this.ldbh = ldbh;
    }

    public String getLfsx() {
        return lfsx;
    }

    public void setLfsx(String lfsx) {
        this.lfsx = lfsx;
    }

    public String getZhs() {
        return zhs;
    }

    public void setZhs(String zhs) {
        this.zhs = zhs;
    }

    public String getYjsl() {
        return yjsl;
    }

    public void setYjsl(String yjsl) {
        this.yjsl = yjsl;
    }

    public String getWyjsl() {
        return wyjsl;
    }

    public void setWyjsl(String wyjsl) {
        this.wyjsl = wyjsl;
    }

    public String getGxsl() {
        return gxsl;
    }

    public void setGxsl(String gxsl) {
        this.gxsl = gxsl;
    }
}
