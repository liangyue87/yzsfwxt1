package com.ljx.hfgsjt.entity.wtys;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "YS_ZJQTCB")
public class YsZjqtcbEntity {
    private String id;
    private String zjqtid;
    private String cybm;
    private String zpry;
    private java.util.Date zpsj;
    private String bzpr;
    private Integer sffsdx;
    private String sjhm;

    @Basic
    @Column(name = "SFFSDX")
    public Integer getSffsdx() {
        return sffsdx;
    }

    public void setSffsdx(Integer sffsdx) {
        this.sffsdx = sffsdx;
    }

    @Basic
    @Column(name = "SJHM")
    public String getSjhm() {
        return sjhm;
    }

    public void setSjhm(String sjhm) {
        this.sjhm = sjhm;
    }

    @Id
    @Column(name = "ID")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "ZJQTID")
    public String getZjqtid() {
        return zjqtid;
    }

    public void setZjqtid(String zjqtid) {
        this.zjqtid = zjqtid;
    }

    @Basic
    @Column(name = "CYBM")
    public String getCybm() {
        return cybm;
    }

    public void setCybm(String cybm) {
        this.cybm = cybm;
    }

    @Basic
    @Column(name = "ZPRY")
    public String getZpry() {
        return zpry;
    }

    public void setZpry(String zpry) {
        this.zpry = zpry;
    }

    @Basic
    @Column(name = "ZPSJ")
    public java.util.Date getZpsj() {
        return zpsj;
    }

    public void setZpsj(java.util.Date zpsj) {
        this.zpsj = zpsj;
    }

    @Basic
    @Column(name = "BZPR")
    public String getBzpr() {
        return bzpr;
    }

    public void setBzpr(String bzpr) {
        this.bzpr = bzpr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        YsZjqtcbEntity that = (YsZjqtcbEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(zjqtid, that.zjqtid) &&
                Objects.equals(cybm, that.cybm) &&
                Objects.equals(zpry, that.zpry) &&
                Objects.equals(zpsj, that.zpsj) &&
                Objects.equals(bzpr, that.bzpr) &&
                Objects.equals(sffsdx, that.zpsj) &&
                Objects.equals(sjhm, that.sjhm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, zjqtid, cybm, zpry, zpsj, bzpr,sffsdx,sjhm);
    }
}
