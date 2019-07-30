package com.ljx.hfgsjt.entity.wtys;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "YS_ZJQTZB")
public class YsZjqtzbEntity {
    private String id;
    private String ysrwzbid;
    private String fqr;
    private java.util.Date fqsj;
    private Long qtzt;
    private java.util.Date qtsj;
    private String qtjg;
    private String bz;
    private java.util.Date yyqtsj;
    private String rwbh;

    @Basic
    @Column(name = "RWBH")
    public String getRwbh() {
        return rwbh;
    }

    public void setRwbh(String rwbh) {
        this.rwbh = rwbh;
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
    @Column(name = "YSRWZBID")
    public String getYsrwzbid() {
        return ysrwzbid;
    }

    public void setYsrwzbid(String ysrwzbid) {
        this.ysrwzbid = ysrwzbid;
    }

    @Basic
    @Column(name = "FQR")
    public String getFqr() {
        return fqr;
    }

    public void setFqr(String fqr) {
        this.fqr = fqr;
    }

    @Basic
    @Column(name = "FQSJ")
    public Date getFqsj() {
        return fqsj;
    }

    public void setFqsj(Date fqsj) {
        this.fqsj = fqsj;
    }

    @Basic
    @Column(name = "QTZT")
    public Long getQtzt() {
        return qtzt;
    }

    public void setQtzt(Long qtzt) {
        this.qtzt = qtzt;
    }

    @Basic
    @Column(name = "QTSJ")
    public Date getQtsj() {
        return qtsj;
    }

    public void setQtsj(Date qtsj) {
        this.qtsj = qtsj;
    }

    @Basic
    @Column(name = "QTJG")
    public String getQtjg() {
        return qtjg;
    }

    public void setQtjg(String qtjg) {
        this.qtjg = qtjg;
    }

    @Basic
    @Column(name = "BZ")
    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    @Basic
    @Column(name = "YYQTSJ")
    public Date getYyqtsj() {
        return yyqtsj;
    }

    public void setYyqtsj(Date yyqtsj) {
        this.yyqtsj = yyqtsj;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        YsZjqtzbEntity that = (YsZjqtzbEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(ysrwzbid, that.ysrwzbid) &&
                Objects.equals(fqr, that.fqr) &&
                Objects.equals(fqsj, that.fqsj) &&
                Objects.equals(qtzt, that.qtzt) &&
                Objects.equals(qtsj, that.qtsj) &&
                Objects.equals(qtjg, that.qtjg) &&
                Objects.equals(bz, that.bz) &&
                Objects.equals(yyqtsj, that.yyqtsj) &&
                Objects.equals(rwbh, that.rwbh);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ysrwzbid, fqr, fqsj, qtzt, qtsj, qtjg, bz, yyqtsj, rwbh);
    }
}
