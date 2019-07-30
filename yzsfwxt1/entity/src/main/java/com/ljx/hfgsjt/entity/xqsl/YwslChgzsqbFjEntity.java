package com.ljx.hfgsjt.entity.xqsl;

import javax.persistence.*;
import java.sql.Time;
import java.util.Objects;

@Entity
@Table(name = "YWSL_CHGZSQB_FJ")
public class YwslChgzsqbFjEntity {
    private String id;
    private String ywslChgzsqbid;
    private String fjlxYj;
    private String fjlxEj;
    private String fjlj;
    private String fjhz;
    private String fjmc;
    private String scr;
    private Time scsj;
    private Long scFlag;
    private String shcr;
    private Time shcsj;

    @Id
    @Column(name = "ID")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "YWSL_CHGZSQBID")
    public String getYwslChgzsqbid() {
        return ywslChgzsqbid;
    }

    public void setYwslChgzsqbid(String ywslChgzsqbid) {
        this.ywslChgzsqbid = ywslChgzsqbid;
    }

    @Basic
    @Column(name = "FJLX_YJ")
    public String getFjlxYj() {
        return fjlxYj;
    }

    public void setFjlxYj(String fjlxYj) {
        this.fjlxYj = fjlxYj;
    }

    @Basic
    @Column(name = "FJLX_EJ")
    public String getFjlxEj() {
        return fjlxEj;
    }

    public void setFjlxEj(String fjlxEj) {
        this.fjlxEj = fjlxEj;
    }

    @Basic
    @Column(name = "FJLJ")
    public String getFjlj() {
        return fjlj;
    }

    public void setFjlj(String fjlj) {
        this.fjlj = fjlj;
    }

    @Basic
    @Column(name = "FJHZ")
    public String getFjhz() {
        return fjhz;
    }

    public void setFjhz(String fjhz) {
        this.fjhz = fjhz;
    }

    @Basic
    @Column(name = "FJMC")
    public String getFjmc() {
        return fjmc;
    }

    public void setFjmc(String fjmc) {
        this.fjmc = fjmc;
    }

    @Basic
    @Column(name = "SCR")
    public String getScr() {
        return scr;
    }

    public void setScr(String scr) {
        this.scr = scr;
    }

    @Basic
    @Column(name = "SCSJ")
    public Time getScsj() {
        return scsj;
    }

    public void setScsj(Time scsj) {
        this.scsj = scsj;
    }

    @Basic
    @Column(name = "SC_FLAG")
    public Long getScFlag() {
        return scFlag;
    }

    public void setScFlag(Long scFlag) {
        this.scFlag = scFlag;
    }

    @Basic
    @Column(name = "SHCR")
    public String getShcr() {
        return shcr;
    }

    public void setShcr(String shcr) {
        this.shcr = shcr;
    }

    @Basic
    @Column(name = "SHCSJ")
    public Time getShcsj() {
        return shcsj;
    }

    public void setShcsj(Time shcsj) {
        this.shcsj = shcsj;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        YwslChgzsqbFjEntity that = (YwslChgzsqbFjEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(ywslChgzsqbid, that.ywslChgzsqbid) &&
                Objects.equals(fjlxYj, that.fjlxYj) &&
                Objects.equals(fjlxEj, that.fjlxEj) &&
                Objects.equals(fjlj, that.fjlj) &&
                Objects.equals(fjhz, that.fjhz) &&
                Objects.equals(fjmc, that.fjmc) &&
                Objects.equals(scr, that.scr) &&
                Objects.equals(scsj, that.scsj) &&
                Objects.equals(scFlag, that.scFlag) &&
                Objects.equals(shcr, that.shcr) &&
                Objects.equals(shcsj, that.shcsj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ywslChgzsqbid, fjlxYj, fjlxEj, fjlj, fjhz, fjmc, scr, scsj, scFlag, shcr, shcsj);
    }
}
