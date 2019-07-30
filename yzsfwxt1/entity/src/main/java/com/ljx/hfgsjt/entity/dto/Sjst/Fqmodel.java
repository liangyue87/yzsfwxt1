package com.ljx.hfgsjt.entity.dto.Sjst;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "XM_LDFQ")
public class Fqmodel {
    @Id
    private String id;
    private String xmid;
    private String ldid;
    private String fqbh;
    private int qslc;
    private int zdlc;
    private double sybz;
    private String fqmc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getXmid() {
        return xmid;
    }

    public void setXmid(String xmid) {
        this.xmid = xmid;
    }

    public String getLdid() {
        return ldid;
    }

    public void setLdid(String ldid) {
        this.ldid = ldid;
    }

    public String getFqbh() {
        return fqbh;
    }

    public void setFqbh(String fqbh) {
        this.fqbh = fqbh;
    }

    public int getQslc() {
        return qslc;
    }

    public void setQslc(int qslc) {
        this.qslc = qslc;
    }

    public int getZdlc() {
        return zdlc;
    }

    public void setZdlc(int zdlc) {
        this.zdlc = zdlc;
    }

    public double getSybz() {
        return sybz;
    }

    public void setSybz(double sybz) {
        this.sybz = sybz;
    }

    public String getFqmc() {
        return fqmc;
    }

    public void setFqmc(String fqmc) {
        this.fqmc = fqmc;
    }
}
