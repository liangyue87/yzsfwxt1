package com.ljx.hfgsjt.entity.dto;

import javax.persistence.*;

@Entity
@Table(name = "XTGL_FZYH")
public class BankEntity {

    @Id
    @GeneratedValue
    private String bh;

    @Column(name = "mc", length = 100)
    private String mc;

    @Column(name = "yhbh", length = 8)
    private String yhbh;

    @Column(name = "yhmc", length = 100)
    private String yhmc;

    public String getBh() {
        return bh;
    }

    public void setBh(String bh) {
        this.bh = bh;
    }

    public String getMc() {
        return mc;
    }

    public void setMc(String mc) {
        this.mc = mc;
    }

    public String getYhbh() {
        return yhbh;
    }

    public void setYhbh(String yhbh) {
        this.yhbh = yhbh;
    }

    public String getYhmc() {
        return yhmc;
    }

    public void setYhmc(String yhmc) {
        this.yhmc = yhmc;
    }
}
