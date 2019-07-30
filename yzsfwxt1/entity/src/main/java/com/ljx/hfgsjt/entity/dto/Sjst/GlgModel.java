package com.ljx.hfgsjt.entity.dto.Sjst;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "XM_GD_GLG")
public class GlgModel {
    @Id
    private String id;
    private String xmgdid;
    private String glgbh;
    private String glgmc;
    private String glgkj;
    private int sl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getXmgdid() {
        return xmgdid;
    }

    public void setXmgdid(String xmgdid) {
        this.xmgdid = xmgdid;
    }

    public String getGlgbh() {
        return glgbh;
    }

    public void setGlgbh(String glgbh) {
        this.glgbh = glgbh;
    }

    public String getGlgmc() {
        return glgmc;
    }

    public void setGlgmc(String glgmc) {
        this.glgmc = glgmc;
    }

    public String getGlgkj() {
        return glgkj;
    }

    public void setGlgkj(String glgkj) {
        this.glgkj = glgkj;
    }

    public int getSl() {
        return sl;
    }

    public void setSl(int sl) {
        this.sl = sl;
    }
}
